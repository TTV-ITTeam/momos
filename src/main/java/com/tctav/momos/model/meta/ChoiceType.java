package com.tctav.momos.model.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties("handler")
public class ChoiceType {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}