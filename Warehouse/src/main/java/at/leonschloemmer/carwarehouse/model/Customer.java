package at.leonschloemmer.carwarehouse.model;

import javax.persistence.Entity;

@Entity
public class Customer extends Person {

    public Customer() {
    }

    public Customer(String name) {
        super(name);
    }
}
