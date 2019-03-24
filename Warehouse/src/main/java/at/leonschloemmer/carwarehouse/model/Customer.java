package at.leonschloemmer.carwarehouse.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
@NamedQuery(name = "Customer.findById", query = "select c from Customer c where c.id = :id")
@NamedQuery(name = "Customer.findByCustomerId", query = "select c from  Customer c where c.customerId = :id")
@NamedQuery(name = "Customer.findByName", query = "select c from Customer c where lower(c.name) like lower(:name)")
public class Customer extends Person {

    private String customerId;

    public Customer() {
    }

    public Customer(String name, String customerId) {
        super(name);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
