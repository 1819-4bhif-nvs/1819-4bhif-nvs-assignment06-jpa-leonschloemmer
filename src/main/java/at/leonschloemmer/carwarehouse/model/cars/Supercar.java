package at.leonschloemmer.carwarehouse.model.cars;

import at.leonschloemmer.carwarehouse.model.Drive;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supercar")
public class Supercar extends Car {

    //region Properties
    private double trickleChargerVoltage;
    private String oilGrade;
    private String fuelGrade;
    private LocalDate lastService;
    private int serviceCycle;
    private int drivingCycle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<Drive> logbook;
    //endregion

    //region Constructors
    public Supercar() {
    }

    public Supercar(int horsepower, int value, int yearProduced, String make, String model, int mileage, LocalDate storageStart, LocalDate storageEnd, String summertyreSpec, double trickleChargerVoltage, String oilGrade, String fuelGrade, LocalDate lastService, int serviceCycle, int drivingCycle, List<Drive> logbook) {
        super(horsepower, value, yearProduced, make, model, mileage, storageStart, storageEnd, summertyreSpec);
        this.trickleChargerVoltage = trickleChargerVoltage;
        this.oilGrade = oilGrade;
        this.fuelGrade = fuelGrade;
        this.lastService = lastService;
        this.serviceCycle = serviceCycle;
        this.drivingCycle = drivingCycle;
        this.logbook = logbook;
    }

    public Supercar(int horsepower, int value, int yearProduced, String make, String model, int mileage, String summertyreSpec, double trickleChargerVoltage, String oilGrade, String fuelGrade, int serviceCycle, int drivingCycle) {
        super(horsepower, value, yearProduced, make, model, mileage, summertyreSpec);
        this.trickleChargerVoltage = trickleChargerVoltage;
        this.oilGrade = oilGrade;
        this.fuelGrade = fuelGrade;
        this.serviceCycle = serviceCycle;
        this.drivingCycle = drivingCycle;
    }

    //endregion

    //region Getters and Setters
    public double getTrickleChargerVoltage() {
        return trickleChargerVoltage;
    }

    public void setTrickleChargerVoltage(double trickleChargerVoltage) {
        this.trickleChargerVoltage = trickleChargerVoltage;
    }

    public String getOilGrade() {
        return oilGrade;
    }

    public void setOilGrade(String oilGrade) {
        this.oilGrade = oilGrade;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public LocalDate getLastService() {
        return lastService;
    }

    public void setLastService(LocalDate lastService) {
        this.lastService = lastService;
    }

    public int getServiceCycle() {
        return serviceCycle;
    }

    public void setServiceCycle(int serviceCycle) {
        this.serviceCycle = serviceCycle;
    }

    public int getDrivingCycle() {
        return drivingCycle;
    }

    public void setDrivingCycle(int drivingCycle) {
        this.drivingCycle = drivingCycle;
    }

    public List<Drive> getLogbook() {
        return logbook;
    }

    public void setLogbook(List<Drive> logbook) {
        this.logbook = logbook;
    }
    //endregion
}
