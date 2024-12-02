package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WeatherEvent;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class Payload {

    private Map<String, Object> unusedData;

    //sensor data and stuff
    private double sensorDistance1;
    private double sensorDistance2;
    private boolean tiltState;
    private double temperature;
    private double humidity;
    private String comfort;
    private boolean wasteReceived;

    //weather data
    private WeatherEvent weather;

    //bin data
    private String address;
    private double longitude;
    private double latitude;
    private String deviceId;


    public Payload(){}

    //GETTERS AND SETTERS

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Map<String, Object> getUnusedData() {
        return unusedData;
    }

    public void setUnusedData(Map<String, Object> unusedData) {
        this.unusedData = unusedData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public WeatherEvent getWeather() {
        return weather;
    }

    public void setWeather(WeatherEvent weather) {
        this.weather = weather;
    }

    public boolean getWasteReceived() {
        return wasteReceived;
    }

    public void setWasteReceived(boolean wasteReceived) {
        this.wasteReceived = wasteReceived;
    }
}
