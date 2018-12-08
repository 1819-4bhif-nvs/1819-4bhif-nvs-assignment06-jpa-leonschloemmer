package at.leonschloemmer.carwarehouse.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "drive")
public class Drive {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int distance;
    private LocalDate date;
    //endregion

    //region Constructors
    public Drive() {
    }

    public Drive(int distance, LocalDate date) {
        this.distance = distance;
        this.date = date;
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    //endregion
}
