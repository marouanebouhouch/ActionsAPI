package com.actions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Tag implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String label;
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    List<Company> companies;

    public Tag() {
    }

    public Tag(String label) {
        this.label = label;
    }

    public Tag(String label, List<Company> companies) {
        this.label = label;
        this.companies = companies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
