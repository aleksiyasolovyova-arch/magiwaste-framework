package be.kdg.magiwastebackend.testing;

public class SensorData {

    private Double sensorDistance1;
    private Double sensorDistance2;
    private boolean tilted;




    public Double getSensorDistance1() {
        return sensorDistance1;
    }

    public void setSensorDistance1(Double sensorDistance1) {
        this.sensorDistance1 = sensorDistance1;
    }

    public Double getSensorDistance2() {
        return sensorDistance2;
    }

    public void setSensorDistance2(Double sensorDistance2) {
        this.sensorDistance2 = sensorDistance2;
    }

    public boolean isTilted() {
        return tilted;
    }

    public void setTilted(boolean tilted) {
        this.tilted = tilted;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "sensorDistance1=" + sensorDistance1 +
                ", sensorDistance2=" + sensorDistance2 +
                ", tilted=" + tilted +
                '}';
    }
}
