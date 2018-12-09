package at.leonschloemmer.carwarehouse.model;

import at.leonschloemmer.carwarehouse.model.cars.Car;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id")
    private Set<StorageSpace> storageSpaces;
    //endregion

    //region Constructors
    public Warehouse() {
    }

    public Warehouse(String location, Set<StorageSpace> storageSpaces) {
        this.setLocation(location);
        this.setStorageSpaces(storageSpaces);
    }

    public Warehouse(String location) {
        this.location = location;
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<StorageSpace> getStorageSpaces() {
        return storageSpaces;
    }

    public void setStorageSpaces(Set<StorageSpace> storageSpaces) {
        this.storageSpaces = storageSpaces;
    }
    //endregion

    public void addStorageSpace(StorageSpace storageSpace) {
        if(storageSpaces == null) {
            storageSpaces = new HashSet<>();
        }
        storageSpaces.add(storageSpace);
    }

    public boolean storeCar(Car car) {
        for(StorageSpace space : storageSpaces) {
            if(space.addCar(car)) {
                return true;
            }
        }
        return false;
    }
}
