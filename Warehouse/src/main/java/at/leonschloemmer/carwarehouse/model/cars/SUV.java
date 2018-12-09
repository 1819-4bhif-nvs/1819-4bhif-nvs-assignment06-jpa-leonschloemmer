package at.leonschloemmer.carwarehouse.model.cars;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "suv")
public class SUV extends Car {

    //region Properties
    private LocalDate lastCleaning;
    private int cleaningCycle;
    private String wintertyreSpec;
    //endregion

    //region Constructors
    public SUV() {
    }

    public SUV(int horsepower, int value, int yearProduced, String make, String model, int mileage, LocalDate storageStart, LocalDate storageEnd, String summertyreSpec, LocalDate lastCleaning, int cleaningCycle, String wintertyreSpec) {
        super(horsepower, value, yearProduced, make, model, mileage, storageStart, storageEnd, summertyreSpec);
        this.setLastCleaning(lastCleaning);
        this.setCleaningCycle(cleaningCycle);
        this.setWintertyreSpec(wintertyreSpec);
    }

    public SUV(int horsepower, int value, int yearProduced, String make, String model, int mileage, String summertyreSpec, int cleaningCycle, String wintertyreSpec) {
        super(horsepower, value, yearProduced, make, model, mileage, summertyreSpec);
        this.cleaningCycle = cleaningCycle;
        this.wintertyreSpec = wintertyreSpec;
    }
    //endregion

    //region Getters and Setters
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

    public String getWintertyreSpec() {
        return wintertyreSpec;
    }

    public void setWintertyreSpec(String wintertyreSpec) {
        this.wintertyreSpec = wintertyreSpec;
    }
    //endregion
}
