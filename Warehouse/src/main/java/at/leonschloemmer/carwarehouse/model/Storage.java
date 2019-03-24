package at.leonschloemmer.carwarehouse.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQuery(name = "Storage.findAll", query = "select s from Storage s")
@NamedQuery(name = "Storage.findByCarId", query = "select s from Storage s where s.car.id = :id")
@NamedQuery(name = "Storage.findByCustomerId", query = "select s from Storage s where s.customer.id = :id")
@NamedQuery(name = "Storage.findByManagerId", query = "select s from Storage s where s.manager.id = :id")
@NamedQuery(name = "Storage.findByWarehouseId", query = "select s from Storage s where s.manager.warehouse.id = :id")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Car car;
    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Manager manager;
    @ManyToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Service service;
    private LocalDate startOfStorage;
    private LocalDate endOfStorage;

    public Storage() {
    }

    public Storage(Car car, Customer customer, Manager manager, Service service, LocalDate startOfStorage, LocalDate endOfStorage) {
        this.car = car;
        this.customer = customer;
        this.manager = manager;
        this.service = service;
        this.startOfStorage = startOfStorage;
        this.endOfStorage = endOfStorage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDate getStartOfStorage() {
        return startOfStorage;
    }

    public void setStartOfStorage(LocalDate startOfStorage) {
        this.startOfStorage = startOfStorage;
    }

    public LocalDate getEndOfStorage() {
        return endOfStorage;
    }

    public void setEndOfStorage(LocalDate endOfStorage) {
        this.endOfStorage = endOfStorage;
    }
}
