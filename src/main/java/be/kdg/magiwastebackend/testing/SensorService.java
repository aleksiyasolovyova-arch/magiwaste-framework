package be.kdg.magiwastebackend.testing;

import java.util.List;

public interface SensorService {
    public void addData(SensorData sensorData);

    public List<SensorData> getSensorData();
}
