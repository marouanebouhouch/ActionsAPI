package com.actions.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("S")
public class Sell extends Operation implements Serializable {
    public Sell() {
    }

    public Sell(int nb_actions, double price_action, Date date, Company company) {
        super(nb_actions, price_action, date, company);
    }
}
