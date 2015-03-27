package com.tctav.momos.controller;

import com.tctav.momos.auth.MomoAuth;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Path;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.security.Authenticated;
import org.wisdom.api.templates.Template;

import static org.wisdom.api.http.HttpMethod.GET;

/**
 * The application controller.
 * Render the application view.
 *
 * @version 0.1
 */
@Controller
@Path("/")
public class AppController extends DefaultController{

    /**
     * The application main view.
     */
    @View("app")
    private Template appView;

    /**
     * @return the application view.
     */
    @Authenticated(MomoAuth.MY_NAME)
    @Route(uri = "",method = GET,produces = "text/html")
    public Result home(){
        return ok(render(appView));
    }
}
