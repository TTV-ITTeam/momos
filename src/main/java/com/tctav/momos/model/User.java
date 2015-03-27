package com.tctav.momos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tctav.momos.model.meta.FormType;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties("handler")
@Entity
public class User {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Id
    private String id;

    private List<FormType> myForms;

    private List<FormType> myEntries;


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //Encrypt password
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
