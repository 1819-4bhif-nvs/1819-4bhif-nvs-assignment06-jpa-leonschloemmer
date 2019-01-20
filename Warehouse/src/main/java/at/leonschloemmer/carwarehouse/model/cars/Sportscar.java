package at.leonschloemmer.carwarehouse.model.cars;

import at.leonschloemmer.carwarehouse.model.people.Customer;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@NamedQuery(name = "Sportscar.findAll", query = "select s from Sportscar s")
public class Sportscar extends Car {

    //region Properties
    private String batterySpec;
    private String fuelGrade;
    private LocalDate lastCleaning;
    private int cleaningCycle;
    //endregion

    //region Constructors
    public Sportscar() {
    }

    public Sportscar(int horsepower, int value, int yearProduced, String make, String model, int mileage, LocalDate storageStart, LocalDate storageEnd, String summertyreSpec, String batterySpec, String fuelGrade, LocalDate lastCleaning, int cleaningCycle) {
        super(horsepower, value, yearProduced, make, model, mileage, storageStart, storageEnd, summertyreSpec);
        this.setBatterySpec(batterySpec);
        this.setFuelGrade(fuelGrade);
        this.setLastCleaning(lastCleaning);
        this.setCleaningCycle(cleaningCycle);
    }

    public Sportscar(int horsepower, int value, int yearProduced, String make, String model, int mileage, String summertyreSpec, String batterySpec, String fuelGrade, int cleaningCycle) {
        super(horsepower, value, yearProduced, make, model, mileage, summertyreSpec);
        this.batterySpec = batterySpec;
        this.fuelGrade = fuelGrade;
        this.cleaningCycle = cleaningCycle;
    }

    public Sportscar(int horsepower, int value, int yearProduced, String make, String model, int mileage, String summertyreSpec, String batterySpec, String fuelGrade, int cleaningCycle, Customer customer) {
        super(horsepower, value, yearProduced, make, model, mileage, summertyreSpec, customer);
        this.batterySpec = batterySpec;
        this.fuelGrade = fuelGrade;
        this.cleaningCycle = cleaningCycle;
    }

    //endregion

    //region Getters and Setters
    public String getBatterySpec() {
        return batterySpec;
    }

    public void setBatterySpec(String batterySpec) {
        this.batterySpec = batterySpec;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public LocalDate getLastCleaning() {
        return lastCleaning;
    }

    public void setLastCleaning(LocalDate lastCleaning) {
        this.lastCleaning = lastCleaning;
    }

    public int getCleaningCycle() {
        return cleaningCycle;
    }

    public void setCleaningCycle(int cleaningCycle) {
        this.cleaningCycle = cleaningCycle;
    }
    //endregion
}
