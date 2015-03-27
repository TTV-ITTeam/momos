package com.tctav.momos.model.inst;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tctav.momos.model.User;
import com.tctav.momos.model.meta.TextType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

/**
 */
@Entity
@JsonIgnoreProperties("handler")
public class Text {
    @Id
    private String id;

    @OneToMany
    private Form form;

    @ManyToOne
    private TextType type;

    @ManyToOne
    private User author;

    private String content;

    private Date creationDate;

    private Date modificationDate;
}
