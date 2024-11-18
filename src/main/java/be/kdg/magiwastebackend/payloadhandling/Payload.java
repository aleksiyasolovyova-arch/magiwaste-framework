package be.kdg.magiwastebackend.payloadhandling;

class Payload {

    private float sensorDistance1;
    private float sensorDistance2;
    private boolean tiltState;
    private String temperature;
    private String humidity;
    private String comfort;

    //one type of payload we can have, payload logic will have to check for nulls in the future
    //TODO: figure out how to have PayloadCleaner handle all constructors
    public Payload(float sensorDistance1, float sensorDistance2, boolean tiltState) {
        this.sensorDistance1 = sensorDistance1;
        this.sensorDistance2 = sensorDistance2;
        this.tiltState = tiltState;
    }

    public Payload(){} //TODO: remove this (testing only~)

    //GETTERS AND SETTERS

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
