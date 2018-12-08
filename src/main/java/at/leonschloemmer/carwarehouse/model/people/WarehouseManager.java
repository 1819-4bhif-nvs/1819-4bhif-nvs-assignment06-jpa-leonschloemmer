package at.leonschloemmer.carwarehouse.model.people;

import at.leonschloemmer.carwarehouse.model.Warehouse;
import at.leonschloemmer.carwarehouse.model.cars.Car;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class WarehouseManager extends Person {

    //region Properties
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Warehouse> warehouses;
    //endregion

    //region Constructors
    public WarehouseManager() {
    }

    public WarehouseManager(String name) {
        super(name);
    }

    public WarehouseManager(String name, List<Warehouse> warehouses) {
        super(name);
        this.setWarehouses(warehouses);
    }
    //endregion

    //region Getters and Setters
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
    //endregion

    public void addWarehouse(Warehouse warehouse) {
        if(warehouses == null) {
            warehouses = new LinkedList<>();
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
