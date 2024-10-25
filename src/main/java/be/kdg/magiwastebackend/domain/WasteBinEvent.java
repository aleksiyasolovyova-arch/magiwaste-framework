package be.kdg.magiwastebackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class WasteBinEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "bin")
    private WasteBin bin;

    private float percentOfVolume;
    private double sensorDistance;
    private boolean isFull;
    private LocalDate eventDate;

    public WasteBinEvent() {
    }

    public WasteBinEvent(WasteBin bin, float percentOfVolume, double sensorDistance, boolean isFull, LocalDate eventDate) {
        this.bin = bin;
        this.percentOfVolume = percentOfVolume;
        this.sensorDistance = sensorDistance;
        this.isFull = isFull;
        this.eventDate = eventDate;
    }

    public WasteBinEvent(Long id, WasteBin bin, float percentOfVolume, double sensorDistance, boolean isFull, LocalDate eventDate) {
        this.id = id;
        this.bin = bin;
        this.percentOfVolume = percentOfVolume;
        this.sensorDistance = sensorDistance;
        this.isFull = isFull;
        this.eventDate = eventDate;
    }

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

    public double getSensorDistance() {
        return sensorDistance;
    }

    public void setSensorDistance(double sensorDistance) {
        this.sensorDistance = sensorDistance;
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
