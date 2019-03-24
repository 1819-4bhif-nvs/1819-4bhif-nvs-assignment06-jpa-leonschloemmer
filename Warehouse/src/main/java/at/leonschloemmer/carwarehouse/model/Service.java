package at.leonschloemmer.carwarehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service;
    private int repetitionDistance;

    public Service() {
    }

    public Service(String service, int repetitionDistance) {
        this.service = service;
        this.repetitionDistance = repetitionDistance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getRepetitionDistance() {
        return repetitionDistance;
    }

    public void setRepetitionDistance(int repetitionDistance) {
        this.repetitionDistance = repetitionDistance;
    }
}
