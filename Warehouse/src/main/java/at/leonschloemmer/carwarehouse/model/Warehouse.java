package at.leonschloemmer.carwarehouse.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Warehouse.findAll", query = "select w from Warehouse w")
@NamedQuery(name = "Warehouse.findAllWManagers", query = "select w from Warehouse w join fetch w.managers")
@NamedQuery(name = "Warehouse.findByLocation", query = "select w from Warehouse w where lower(w.location) = lower(:location)")
@NamedQuery(name = "Warehouse.findById", query = "select w from Warehouse w where w.id = :id")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WAREHOUSE_ID")
    private Long id;
    private String location;
    @OneToMany( fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JsonbTransient
    private Set<Manager> managers;

    public Warehouse() {
        setManagers(new HashSet<>());
    }

    public Warehouse(String location) {
        this();
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }
}
