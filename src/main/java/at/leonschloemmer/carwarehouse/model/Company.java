package at.leonschloemmer.carwarehouse.model;

import at.leonschloemmer.carwarehouse.model.cars.Car;
import at.leonschloemmer.carwarehouse.model.people.WarehouseManager;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<WarehouseManager> managers;
    //endregion

    //region Constructors
    public Company() {
    }

    public Company(List<WarehouseManager> managers) {
        this.setManagers(managers);
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public List<WarehouseManager> getManagers() {
        return managers;
    }

    public void setManagers(List<WarehouseManager> managers) {
        this.managers = managers;
    }
    //endregion

    public void addWarehouseManager(WarehouseManager manager) {
        if(managers == null) {
            managers = new LinkedList<>();
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
