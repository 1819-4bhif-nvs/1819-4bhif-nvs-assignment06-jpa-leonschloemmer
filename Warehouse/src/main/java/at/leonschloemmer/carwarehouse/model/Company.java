package at.leonschloemmer.carwarehouse.model;

import at.leonschloemmer.carwarehouse.model.cars.Car;
import at.leonschloemmer.carwarehouse.model.people.WarehouseManager;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Set<WarehouseManager> managers;
    //endregion

    //region Constructors
    public Company() {
    }

    public Company(Set<WarehouseManager> managers) {
        this.setManagers(managers);
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public Set<WarehouseManager> getManagers() {
        return managers;
    }

    public void setManagers(Set<WarehouseManager> managers) {
        this.managers = managers;
    }
    //endregion

    public void addWarehouseManager(WarehouseManager manager) {
        if(managers == null) {
            managers = new HashSet<>();
        }
        managers.add(manager);
    }

    public void storeCar(Car car, String location) {
        for (WarehouseManager manager : managers) {
            if(manager.storeCar(car, location)) {
                return;
            }
        }
    }
}
