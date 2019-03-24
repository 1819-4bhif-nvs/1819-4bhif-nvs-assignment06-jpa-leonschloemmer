package at.leonschloemmer.carwarehouse.core;

import at.leonschloemmer.carwarehouse.model.*;

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
//        Company company = new Company();
//
//        WarehouseManager earl = new WarehouseManager("Earl");
//        company.addWarehouseManager(earl);
//
//        Warehouse london1 = new Warehouse("London");
//        earl.addWarehouse(london1);
//
//        StorageSpace londonSupercars = new StorageSpace(20);
//        london1.addStorageSpace(londonSupercars);
//        london1.addWarehouseManager(earl);
//
//
//        Customer bill = new Customer("Bill", company);
//
//        Car lamborghiniHuracan = new Supercar(610, 250000, 2017, "Lamborghini", "Huracan", 16328, "Pirelli PZero Corsa 320/10", 5.0, "5w-30", "100 Octane", 300, 15, bill);
//        bill.addCar(lamborghiniHuracan);
//        bill.storeCar(lamborghiniHuracan, "London", LocalDate.now());
//
//        em.persist(company);
//        em.persist(bill);
//        //em.persist(earl);
//        em.persist(london1);
//        em.persist(londonSupercars);
//        em.persist(lamborghiniHuracan);

        Car lambo = new Car("Lamborghini", "Huracan", 610, 250000);
        Customer leon = new Customer("Leon", "LEON01");
        Manager andi = new Manager("Andi");
        Warehouse london = new Warehouse("London");
        london.getManagers().add(andi);

        andi.setWarehouse(london);

        Warehouse huffington = new Warehouse("Huffington");

        Service drive = new Service("Drive 40km", 30);

        Storage storage = new Storage(lambo, leon, andi, drive, LocalDate.of(2019, 3, 24), LocalDate.of(2019, 4, 24));


        em.persist(lambo);
        em.persist(leon);
        em.persist(london);
        em.persist(andi);
        em.persist(huffington);
        em.persist(drive);
        em.persist(storage);

        //andi.setWarehouse(london);
        //em.merge(andi);

        //london.getManagers().add(andi);


    }

}
