package be.kdg.magiwastebackend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class WasteBinEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) //TODO: Change lazy
    @JoinColumn(name = "bin")
    private WasteBin bin;

    private float percentOfVolume;
    private double sensorDistance1;
    private double sensorDistance2;
    private boolean tiltState;
    private double temperature;
    private float humidity;
    private String comfort;
    private boolean isFull;
    private LocalDate eventDate;

    //CONSTRUCTORS
    public WasteBinEvent() {
    }

    public WasteBinEvent(WasteBin bin, float percentOfVolume, double sensorDistance1, double sensorDistance2, boolean tiltState, float temperature, float humidity, String comfort, boolean isFull, LocalDate eventDate) {
        this.bin = bin;
        this.percentOfVolume = percentOfVolume;
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
        this.temperature = temperature;
        this.humidity = humidity;
        this.comfort = comfort;
        this.isFull = isFull;
        this.eventDate = eventDate;
    }

    public WasteBinEvent(double sensorDistance1, double sensorDistance2, boolean tiltState, double temperature) {
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
        this.temperature = temperature;
    }

    //GETTERS AND SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public WasteBin getBin() {
        return bin;
    }

    public void setBin(WasteBin bin) {
        this.bin = bin;
    }

    public float getPercentOfVolume() {
        return percentOfVolume;
    }

    public void setPercentOfVolume(float percentOfVolume) {
        this.percentOfVolume = percentOfVolume;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
}
