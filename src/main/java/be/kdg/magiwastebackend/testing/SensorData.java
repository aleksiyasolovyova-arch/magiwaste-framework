package be.kdg.magiwastebackend.testing;

public class SensorData {

    private float sensorDistance1;
    private float sensorDistance2;
    private boolean tiltState;
    private String temperature;
    private String humidity;
    private String comfort;


    public SensorData(float sensorDistance1, float sensorDistance2, boolean tiltState) {
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
    }

    public SensorData() {
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

    public boolean getTilted() {
        return tiltState;
    }

    public void setTilted(Boolean tiltState) {
        this.tiltState = tiltState;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }
}
