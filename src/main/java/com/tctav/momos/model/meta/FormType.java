package com.tctav.momos.model.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * The Form template.
 *
 * @author <a href="mailto:jbardin@tech-arts.com">Jonathan M. Bardin</a>
 */
@Entity
@JsonIgnoreProperties("handler")
public class FormType {
    @Id
    private String id;

    @NotNull
    private String title;

    @OneToMany(orphanRemoval = true)
    private List<EntryType> entries;

    private Date creation;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EntryType> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryType> entries) {
        this.entries = entries;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
