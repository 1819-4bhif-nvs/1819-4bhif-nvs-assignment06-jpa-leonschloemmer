package at.leonschloemmer.carwarehouse.model.people;

import at.leonschloemmer.carwarehouse.model.Company;
import at.leonschloemmer.carwarehouse.model.cars.Car;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "customer")
public class Customer {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Car> cars;

    @ManyToOne
    private Company company;
    //endregion

    //region Constructors
    public Customer() {
    }

    public Customer(String name, Company company) {
        setName(name);
        setCompany(company);
    }

    public Customer(String name, List<Car> cars) {
        setName(name);
        this.setCars(cars);
    }
    //endregion

    //region Getters and Setters


    public Long getId() {
        return id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public void storeCar(Car car, String location, LocalDate storagEnd) {
        car.setStorageStart(LocalDate.now());
        car.setStorageEnd(storagEnd);
        company.storeCar(car, location);
    }

    public void addCar(Car car) {
        if(cars == null) {
            cars = new LinkedList<>();
        }
        cars.add(car);
    }


}
