package at.leonschloemmer.carwarehouse.core;

import at.leonschloemmer.carwarehouse.model.Company;
import at.leonschloemmer.carwarehouse.model.StorageSpace;
import at.leonschloemmer.carwarehouse.model.Warehouse;
import at.leonschloemmer.carwarehouse.model.cars.Car;
import at.leonschloemmer.carwarehouse.model.cars.Supercar;
import at.leonschloemmer.carwarehouse.model.people.Customer;
import at.leonschloemmer.carwarehouse.model.people.WarehouseManager;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Singleton
@Startup
public class InitBean {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        Company company = new Company();

        WarehouseManager earl = new WarehouseManager("Earl");
        company.addWarehouseManager(earl);

        Warehouse london1 = new Warehouse("London");
        earl.addWarehouse(london1);

        StorageSpace londonSupercars = new StorageSpace(20);
        london1.addStorageSpace(londonSupercars);


        Customer bill = new Customer("Bill", company);

        Car lamborghiniHuracan = new Supercar(610, 250000, 2017, "Lamborghini", "Huracan", 16328, "Pirelli PZero Corsa 320/10", 5.0, "5w-30", "100 Octane", 300, 15);
        bill.addCar(lamborghiniHuracan);
        bill.storeCar(lamborghiniHuracan, "London", LocalDate.now());

//        em.persist(lamborghiniHuracan);
//        em.persist(londonSupercars);
//        em.persist(london1);
//        em.persist(earl);
//        em.persist(bill);
//        em.persist(company);

        em.persist(company);
        em.persist(bill);
        em.persist(earl);
        em.persist(london1);
        em.persist(londonSupercars);
        em.persist(lamborghiniHuracan);

    }

}
