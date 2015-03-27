package com.tctav.momos.model.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * created: 3/24/15.
 *
 * @author <a href="mailto:jbardin@tech-arts.com">Jonathan M. Bardin</a>
 */
@Entity
@JsonIgnoreProperties("handler")
public class RadioButtonType {

    @Id
    private String id;

    private String label;
}
