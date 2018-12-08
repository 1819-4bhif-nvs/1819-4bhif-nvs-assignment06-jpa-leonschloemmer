package at.leonschloemmer.carwarehouse.model;

import at.leonschloemmer.carwarehouse.model.cars.Car;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "storagespace")
public class StorageSpace {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "storagespace_id")
    private List<Car> cars;
    //endregion

    //region Constructors
    public StorageSpace() {
    }

    public StorageSpace(int capacity) {
        this.setCapacity(capacity);
    }

    public StorageSpace(int capacity, List<Car> cars) {
        this.setCapacity(capacity);
        this.setCars(cars);
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    //endregion

    public boolean addCar(Car car) {
        if(cars == null) {
            cars = new LinkedList<>();
            cars.add(car);
        }
        if(cars.get(0).getClass() == car.getClass() && cars.size() < capacity) {
            cars.add(car);
            return true;
        }
        return false;
    }
}
