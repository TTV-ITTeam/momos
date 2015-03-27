package com.tctav.momos.model.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tctav.momos.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * created: 3/23/15.
 *
 * @author <a href="mailto:jbardin@tech-arts.com">Jonathan M. Bardin</a>
 */
@Entity
@JsonIgnoreProperties("handler")
public class FormType {
    @Id
    private String id;

    private String title;

    private User owner;
}
