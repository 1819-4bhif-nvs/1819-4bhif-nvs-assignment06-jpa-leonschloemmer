package at.leonschloemmer.carwarehouse.model.people;

import at.leonschloemmer.carwarehouse.model.Company;
import at.leonschloemmer.carwarehouse.model.cars.Car;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
public class Customer extends Person {

    //region Properties

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Set<Car> cars;

    @ManyToOne
    private Company company;
    //endregion

    //region Constructors
    public Customer() {
    }

    public Customer(String name, Company company) {
        super(name);
        setCompany(company);
    }

    public Customer(String name, Set<Car> cars) {
        super(name);
        this.setCars(cars);
    }
    //endregion

    //region Getters and Setters

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    //endregion

    public void storeCar(Car car, String location, LocalDate storageEnd) {
        car.setStorageStart(LocalDate.now());
        car.setStorageEnd(storageEnd);
        company.storeCar(car, location);
    }

    public void addCar(Car car) {
        if(cars == null) {
            cars = new HashSet<>();
        }
        cars.add(car);
    }


}
