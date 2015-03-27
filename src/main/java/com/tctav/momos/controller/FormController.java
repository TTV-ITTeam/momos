package com.tctav.momos.controller;

import com.tctav.momos.model.User;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Model;
import org.wisdom.api.annotations.Path;
import org.wisdom.orientdb.object.OrientDbCrud;

/**
 * This controller allows for the client to create/edit/remove Form.
 * It requires for the user to be logged in.
 *
 * @version 0.1
 */
@Controller
@Path("/form")
public class FormController extends DefaultController {

    /**
     * User model.
     */
    @Model(value = User.class)
    private OrientDbCrud<User,String> userCrud;



}
