package com.tctav.momos.auth;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.wisdom.api.http.Context;
import org.wisdom.api.http.Result;
import org.wisdom.api.http.Results;
import org.wisdom.api.security.Authenticator;

/**
 * Simple Authenticator.
 *
 */
@Component
@Provides
@Instantiate
public class MomoAuth implements Authenticator {
    public static final String MY_NAME = "MomoAuth";
    public static final String USERNAME_PROP = "username";
    private static final String ADMIN = "admin";

    public String getName() {
        return MY_NAME;
    }

    public String getUserName(Context context) {
        String username = context.session().get(USERNAME_PROP);

        if (username != null) {
            return username;
        }

        username = context.parameter(USERNAME_PROP);

        if (username != null  && username.equals(ADMIN)) {
            context.session().put(USERNAME_PROP, ADMIN);
            return ADMIN;
        }

        return null;
    }

    public Result onUnauthorized(Context context) {
        return Results.unauthorized("You must be authenticated!");
    }
}
