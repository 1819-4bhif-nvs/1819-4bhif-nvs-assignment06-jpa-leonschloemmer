package at.leonschloemmer.carwarehouse.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Manager.findAll", query = "select m from Manager m join fetch m.warehouse")
@NamedQuery(name = "Manager.findById", query = "select m from Manager m join fetch m.warehouse where m.id = :id")
public class Manager extends Person {

    @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private Warehouse warehouse;

    public Manager() {
    }

    public Manager(String name) {
        super(name);
    }

    public Manager(String name, Warehouse warehouse) {
        super(name);
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
