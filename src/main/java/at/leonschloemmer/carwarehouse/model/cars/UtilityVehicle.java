package at.leonschloemmer.carwarehouse.model.cars;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "utilityvehicle")
public class UtilityVehicle extends Car {

    //region Properties
    private String wintertyreSpec;
    private String fuelType;
    //endregion

    //region Constructors
    public UtilityVehicle() {
    }

    public UtilityVehicle(int horsepower, int value, int yearProduced, String make, String model, int mileage, LocalDate storageStart, LocalDate storageEnd, String summertyreSpec, String wintertyreSpec, String fuelType) {
        super(horsepower, value, yearProduced, make, model, mileage, storageStart, storageEnd, summertyreSpec);
        this.setWintertyreSpec(wintertyreSpec);
        this.setFuelType(fuelType);
    }

    public UtilityVehicle(int horsepower, int value, int yearProduced, String make, String model, int mileage, String summertyreSpec, String wintertyreSpec, String fuelType) {
        super(horsepower, value, yearProduced, make, model, mileage, summertyreSpec);
        this.wintertyreSpec = wintertyreSpec;
        this.fuelType = fuelType;
    }

    //endregion

    //region Getters and Setters
    public String getWintertyreSpec() {
        return wintertyreSpec;
    }

    public void setWintertyreSpec(String wintertyreSpec) {
        this.wintertyreSpec = wintertyreSpec;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    //endregion
}