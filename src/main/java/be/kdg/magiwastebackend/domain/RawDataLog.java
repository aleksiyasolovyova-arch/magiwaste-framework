package be.kdg.magiwastebackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawDataLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO add attribute for bin id

    private double sensorDistance1;
    private double sensorDistance2;
    private boolean tiltState;
    private double temperature;
    private double humidity;
    private String comfort;
    //this field stores the unused controller payload keys and values as a json string. yikes!
    private String unusedData;

    public RawDataLog(double sensorDistance1, double sensorDistance2, boolean tiltState, double temperature, double humidity, String comfort, String unusedData) {
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
        this.temperature = temperature;
        this.humidity = humidity;
        this.comfort = comfort;
        this.unusedData = unusedData;
    }

    public RawDataLog(float sensorDistance1, float sensorDistance2, boolean tiltState, float temperature) {
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
        this.temperature = temperature;
    }

    public RawDataLog(float sensorDistance1, float sensorDistance2, boolean tiltState){
        this.sensorDistance1 = sensorDistance1;
        this. sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
    }

    public RawDataLog() {

    }

    public RawDataLog(double sensorDistance1, double sensorDistance2, boolean tiltState, double temperature, String unusedDataJsonString) {
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
        this.temperature = temperature;
        this.unusedData = unusedDataJsonString;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getSensorDistance1() {
        return sensorDistance1;
    }

    public void setSensorDistance1(double sensorDistance1) {
        this.sensorDistance1 = sensorDistance1;
    }

    public double getSensorDistance2() {
        return sensorDistance2;
    }

    public void setSensorDistance2(double sensorDistance2) {
        this.sensorDistance2 = sensorDistance2;
    }

    public boolean isTiltState() {
        return tiltState;
    }

    public boolean getTilted() {
        return tiltState;
    }

    public void setTiltState(boolean tiltState) {
        this.tiltState = tiltState;
    }
}