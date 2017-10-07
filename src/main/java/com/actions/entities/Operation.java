package com.actions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="operation_type",discriminatorType=DiscriminatorType.STRING,length=1)
public class Operation implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private int nb_actions;
    private double price_action;
    private Date date_action;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    public Operation() {
    }

    public Operation(int nb_actions, double price_action, Date date_action, Company company) {
        this.nb_actions = nb_actions;
        this.price_action = price_action;
        this.date_action = date_action;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNb_actions() {
        return nb_actions;
    }

    public void setNb_actions(int nb_actions) {
        this.nb_actions = nb_actions;
    }

    public double getPrice_action() {
        return price_action;
    }

    public void setPrice_action(double price_action) {
        this.price_action = price_action;
    }

    public Date getDate_action() {
        return date_action;
    }

    public void setDate_action(Date date_action) {
        this.date_action = date_action;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
