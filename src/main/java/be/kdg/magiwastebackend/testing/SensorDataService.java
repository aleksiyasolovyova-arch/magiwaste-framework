package be.kdg.magiwastebackend.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDataService implements SensorService {


    List<SensorData> sensorDataList = new ArrayList<>();

    @Override
    public void addData(SensorData sensorData) {
        sensorDataList.add(sensorData);
    }

    @Override
    public List<SensorData> getSensorData() {
        return sensorDataList;
    }
}
