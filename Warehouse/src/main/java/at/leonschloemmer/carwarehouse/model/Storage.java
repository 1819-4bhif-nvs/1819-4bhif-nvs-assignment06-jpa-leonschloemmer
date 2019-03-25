package at.leonschloemmer.carwarehouse.model;

import at.leonschloemmer.carwarehouse.core.LocalDateXmlAdapter;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Storage.findAll", query = "select s from Storage s join fetch s.manager")
@NamedQuery(name = "Storage.findById", query = "select s from Storage s where s.id = :id")
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
    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Set<Service> services;
    @JsonbTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate startOfStorage;
    @JsonbTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate endOfStorage;

    public Storage() {
        services = new HashSet<>();
    }

    public Storage(Car car, Customer customer, Manager manager, LocalDate startOfStorage, LocalDate endOfStorage) {
        this();
        this.car = car;
        this.customer = customer;
        this.manager = manager;
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

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
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
