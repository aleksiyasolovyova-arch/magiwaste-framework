package be.kdg.magiwastebackend.domain;

import be.kdg.magiwastebackend.service.RawDataLogService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawDataLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float sensorDistance1;
    private float sensorDistance2;
    private boolean tiltState;

    public RawDataLog(float sensorDistance1, float sensorDistance2, boolean tiltState){
        this.sensorDistance1 = sensorDistance1;
        this. sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public float getSensorDistance1() {
        return sensorDistance1;
    }

    public void setSensorDistance1(float sensorDistance1) {
        this.sensorDistance1 = sensorDistance1;
    }

    public float getSensorDistance2() {
        return sensorDistance2;
    }

    public void setSensorDistance2(float sensorDistance2) {
        this.sensorDistance2 = sensorDistance2;
    }

    public boolean isTiltState() {
        return tiltState;
    }

    public void setTiltState(boolean tiltState) {
        this.tiltState = tiltState;
    }
}