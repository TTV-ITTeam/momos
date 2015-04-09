package com.tctav.momos.model.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@JsonIgnoreProperties("handler")
public class EntryType {
    private String type;
    private String label;

    @OneToMany(orphanRemoval = true)
    private List<ChoiceType> choices;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ChoiceType> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceType> choices) {
        this.choices = choices;
    }
}