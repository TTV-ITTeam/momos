package com.tctav.momos.controller;

import com.tctav.momos.auth.MomoAuth;
import com.tctav.momos.model.User;
import javassist.util.proxy.Proxy;
import org.apache.felix.ipojo.annotations.Requires;
import org.mindrot.jbcrypt.BCrypt;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.Result;
import org.wisdom.api.router.Router;
import org.wisdom.api.security.Authenticator;
import org.wisdom.api.templates.Template;
import org.wisdom.orientdb.object.OrientDbCrud;

import static org.wisdom.api.http.HttpMethod.GET;
import static org.wisdom.api.http.HttpMethod.POST;

/**
 * created: 3/24/15.
 *
 * @author <a href="mailto:jbardin@tech-arts.com">Jonathan M. Bardin</a>
 */
@Controller
@Path("/session")
public class SessionController extends DefaultController{
    static {Class workaround = Proxy.class;}

    @Model(value = User.class)
    private OrientDbCrud<User,String> userCrud;

    @View("login")
    private Template loginView;

    @Requires
    private Router router;

    @Requires
    private Authenticator auth;

    /**
     * Render the login loginView.
     * @return The login loginView.
     */
    @Route(uri = "/login",method = GET,produces = "text/html")
    public Result page(){
        return ok(render(loginView));
    }

    /**
     * Create a session for the user with the given credentials
     * @param email user email
     * @param pass  user password
     * @return unauthorized if the credentials does not match an existing user, ok otherwise.
     */
    @Route(uri = "/login",method = POST,accepts = "application/x-www-form-urlencoded",produces = "text/html")
    public Result login(@FormParameter("email") final String email,@FormParameter("pass") final String pass){

        if(auth.getUserName(context())!=null){
            session().clear(); //logout if he retry to login
        }


        if(email == null || pass == null){
            return ok(render(loginView,"error","Please input your credential."));
        }

        //Should use an index on email :P
        User user = userCrud.findOne(user1 ->
            user1.getEmail().equals(email) && BCrypt.checkpw(pass, user1.getPassword())
        );


        if(user == null){
            return ok(render(loginView, "error", "Bad credential."));
        }

        session(MomoAuth.USERNAME_PROP, user.getId());
        session("name", user.getName());
        return redirect(router.getReverseRouteFor(AppController.class,"home"));
    }

    /**
     * Logout previously logged in user.
     * @return ok
     */
    @Route(uri = "/logout", method = GET)
    public Result logout(){
        session().clear();
        return ok("You have successfully logout");
    }
}
