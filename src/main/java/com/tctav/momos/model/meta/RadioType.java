package com.tctav.momos.model.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * created: 3/23/15.
 *
 * @author <a href="mailto:jbardin@tech-arts.com">Jonathan M. Bardin</a>
 */
@Entity
@JsonIgnoreProperties("handler")
public class RadioType implements EntryType{
    @Id
    private String id;

    private String label;

    private String description;

    private int position;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
    private List<RadioButtonType> choices;
}
