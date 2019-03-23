package at.leonschloemmer.carwarehouse.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = "Car.findAll", query = "select c from Car c")
@NamedQuery(name = "Car.findById", query = "select c from Car c where c.id = :id")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
    private Long id;
    private String make;
    private String model;
    private int horsepower;
    private int value;

    public Car() {
    }

    public Car(Long id, String make, String model, int horsepower, int value) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.horsepower = horsepower;
        this.value = value;
    }

    public Car(String make, String model, int horsepower, int value) {
        this.make = make;
        this.model = model;
        this.horsepower = horsepower;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
