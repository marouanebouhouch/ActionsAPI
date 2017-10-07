package com.actions.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("B")
public class Buy extends Operation implements Serializable {
    public Buy() {
    }

    public Buy(int nb_actions, double price_action, Date date, Company company) {
        super(nb_actions, price_action, date, company);
    }
}
