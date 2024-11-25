package be.kdg.magiwastebackend.domain;

import be.kdg.magiwastebackend.weatherapi.WeatherEvent;
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

    private double percentOfVolume;
    private double sensorDistance1;
    private double sensorDistance2;
    private boolean tiltState;
    private double temperature;
    private double humidity;
    private String comfort;
    private boolean isFull;
    private LocalDate eventDate;

    @OneToOne
    private WeatherEvent weatherEvent;

    //CONSTRUCTORS
    public WasteBinEvent() {
    }

    public WasteBinEvent(WasteBin bin, float percentOfVolume, double sensorDistance1, double sensorDistance2, boolean tiltState, double temperature, double humidity, String comfort, boolean isFull, LocalDate eventDate) {
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

    public double getPercentOfVolume() {
        return percentOfVolume;
    }

    public void setPercentOfVolume(double percentOfVolume) {
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

    public void setTiltState(boolean tiltState) {
        this.tiltState = tiltState;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public WeatherEvent getWeatherEvent() {
        return weatherEvent;
    }

    public void setWeatherEvent(WeatherEvent weatherEvent) {
        this.weatherEvent = weatherEvent;
    }
}
