package com.tctav.momos.controller;

import com.google.common.collect.Iterables;
import com.orientechnologies.orient.core.id.ORecordId;
import com.tctav.momos.auth.MomoAuth;
import com.tctav.momos.model.User;
import com.tctav.momos.model.meta.FormType;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.AsyncResult;
import org.wisdom.api.http.Result;
import org.wisdom.api.security.Authenticated;
import org.wisdom.orientdb.object.OrientDbCrud;

import javax.validation.Valid;

import java.util.Calendar;

import static com.tctav.momos.auth.MomoAuth.USERNAME_PROP;
import static org.wisdom.api.http.HttpMethod.GET;
import static org.wisdom.api.http.HttpMethod.PUT;

/**
 * This controller allows for the client to create/edit/remove Form.
 * It requires for the user to be logged in.
 *
 * @version 0.1
 */
@Controller
@Path("/form")
public class FormTypeController extends DefaultController {

    /**
     * User model.
     */
    @Model(value = User.class)
    private OrientDbCrud<User,String> userCrud;

    private OrientDbCrud<FormType,String> formCrud;

    //@Requires
    //private OrientDbDocumentService db;

    @Authenticated(MomoAuth.MY_NAME)
    @Route(method = PUT,uri = "/type",accepts = "application/json")
    public Result putNewFormType(@Valid @Body final FormType formType){
        return new AsyncResult(() -> {
            User owner = userCrud.getRepository().get().acquire().load(new ORecordId(session(USERNAME_PROP)));
            formType.setCreation(Calendar.getInstance().getTime());
            owner.addFormType(formType);
            userCrud.save(owner);
            return ok();
        });
    }

    @Authenticated(MomoAuth.MY_NAME)
    @Route(method = GET,uri = "/type",produces = "application/json")
    public Result get(){
        return new AsyncResult(() -> {
            User owner = userCrud.getRepository().get().acquire().load(new ORecordId(session(USERNAME_PROP)));
            return ok(Iterables.toArray(owner.getFormTypes(),FormType.class)).json();
        });
    }
}
