package at.leonschloemmer.carwarehouse.model.cars;

import at.leonschloemmer.carwarehouse.model.people.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "car")
public abstract class Car {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;
    private int horsepower;
    private int value;
    private int yearProduced;
    private String make;
    private String model;
    private int mileage;
    private LocalDate storageStart;
    private LocalDate storageEnd;
    private String summertyreSpec;
    //endregion

    //region Constructors
    public Car() {

    }

    public Car(int horsepower, int value, int yearProduced, String make, String model, int mileage, LocalDate storageStart, LocalDate storageEnd, String summertyreSpec) {
        this.horsepower = horsepower;
        this.value = value;
        this.yearProduced = yearProduced;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.storageStart = storageStart;
        this.storageEnd = storageEnd;
        this.summertyreSpec = summertyreSpec;
    }

    public Car(int horsepower, int value, int yearProduced, String make, String model, int mileage, String summertyreSpec) {
        this.horsepower = horsepower;
        this.value = value;
        this.yearProduced = yearProduced;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.summertyreSpec = summertyreSpec;
    }

    //endregion

    //region Getters and Setters
    public Long getId() {
        return id;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(int yearProduced) {
        this.yearProduced = yearProduced;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getStorageStart() {
        return storageStart;
    }

    public void setStorageStart(LocalDate storageStart) {
        this.storageStart = storageStart;
    }

    public LocalDate getStorageEnd() {
        return storageEnd;
    }

    public void setStorageEnd(LocalDate storageEnd) {
        this.storageEnd = storageEnd;
    }

    public String getSummertyreSpec() {
        return summertyreSpec;
    }

    public void setSummertyreSpec(String summertyreSpec) {
        this.summertyreSpec = summertyreSpec;
    }
    //endregion

}
