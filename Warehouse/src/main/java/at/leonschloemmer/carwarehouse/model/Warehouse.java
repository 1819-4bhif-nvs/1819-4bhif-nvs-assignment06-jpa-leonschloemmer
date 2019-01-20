package at.leonschloemmer.carwarehouse.model;

import at.leonschloemmer.carwarehouse.model.cars.Car;
import at.leonschloemmer.carwarehouse.model.people.WarehouseManager;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Warehouse.findAll", query = "select w from Warehouse w")
public class Warehouse {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long id;
    private String location;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "warehouses")
    @JsonbTransient
    @XmlTransient
    //@JoinTable(name = "wh_whm", joinColumns = @JoinColumn(name = "warehouse_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    protected Set<WarehouseManager> managers;

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

    public Set<WarehouseManager> getManagers() {
        return managers;
    }

    public void setManagers(Set<WarehouseManager> managers) {
        this.managers = managers;
    }

    //endregion

    public void addStorageSpace(StorageSpace storageSpace) {
        if(storageSpaces == null) {
            storageSpaces = new HashSet<>();
        }
        storageSpaces.add(storageSpace);
    }

    public void addWarehouseManager(WarehouseManager manager) {
        if(managers == null) {
            managers = new HashSet<>();
        }
        managers.add(manager);
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
