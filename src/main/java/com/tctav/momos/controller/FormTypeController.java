package com.tctav.momos.controller;

import com.google.common.collect.Iterables;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tctav.momos.auth.MomoAuth;
import com.tctav.momos.model.User;
import com.tctav.momos.model.meta.FormType;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.Result;
import org.wisdom.api.security.Authenticated;
import org.wisdom.orientdb.object.OrientDbCrud;

import javax.validation.Valid;
import java.util.List;

import static java.util.Calendar.getInstance;
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

    @Model(value = FormType.class)
    private OrientDbCrud<FormType,String> ftypeCrud;

    //@Requires
    //private OrientDbDocumentService db;

    @Authenticated(MomoAuth.MY_NAME)
    @Route(method = PUT,uri = "/type",accepts = "application/json")
    public Result putNewFormType(@Valid @Body FormType formType){
        User owner = userCrud.getRepository().get().acquire().load(new ORecordId(session(MomoAuth.USERNAME_PROP)));
        formType.setOwner(owner);
        formType.setCreation(getInstance().getTime());
        formType = ftypeCrud.save(formType);
        return ok();
    }

    @Authenticated(MomoAuth.MY_NAME)
    @Route(method = GET,uri = "/type",produces = "application/json")
    public Result get(){
        List<FormType> forms = ftypeCrud.query(new OSQLSynchQuery<FormType>("select from FormType where owner="+session(MomoAuth.USERNAME_PROP)));
        return ok(Iterables.toArray(forms,FormType.class)).json();
    }
}
