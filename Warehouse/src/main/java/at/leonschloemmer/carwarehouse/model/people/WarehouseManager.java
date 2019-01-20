package at.leonschloemmer.carwarehouse.model.people;

import at.leonschloemmer.carwarehouse.model.Warehouse;
import at.leonschloemmer.carwarehouse.model.cars.Car;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "WarehouseManager.findAll", query = "select w from WarehouseManager w")
public class WarehouseManager extends Person {

    //region Properties
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "person_id")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Warehouse> warehouses;
    //endregion

    //region Constructors
    public WarehouseManager() {
    }

    public WarehouseManager(String name) {
        super(name);
    }

    public WarehouseManager(String name, Set<Warehouse> warehouses) {
        super(name);
        this.setWarehouses(warehouses);
    }
    //endregion

    //region Getters and Setters
    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
    //endregion

    public void addWarehouse(Warehouse warehouse) {
        if(warehouses == null) {
            warehouses = new HashSet<>();
        }
        warehouses.add(warehouse);
    }

    public boolean storeCar(Car car, String location) {
        for(Warehouse warehouse : warehouses) {
            if(warehouse.getLocation().equals(location)) {
                if(warehouse.storeCar(car)) {
                    return true;
                }
            }
        }
        return false;
    }
}
