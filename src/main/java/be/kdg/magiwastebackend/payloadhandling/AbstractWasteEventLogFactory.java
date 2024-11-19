package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
class AbstractWasteEventLogFactory {
    static WasteBinEvent createWasteBinEvent(Payload payload) {
        double sensorDistance1 = payload.getSensorDistance1();
        double sensorDistance2 = payload.getSensorDistance2();
        boolean tiltState = payload.isTiltState();
        double temperature = payload.getTemperature();

        //TODO add all the other required fields in wasteBinEvent

        return new WasteBinEvent(sensorDistance1, sensorDistance2, tiltState, temperature);
    }

    static RawDataLog createRawDataLog(Payload payload){
        double sensorDistance1 = payload.getSensorDistance1();
        double sensorDistance2 = payload.getSensorDistance2();
        boolean tiltState = payload.isTiltState();
        double temperature = payload.getTemperature();
        double humidity = payload.getHumidity();
        String comfort = payload.getComfort();
        Map<String, Object> unusedData = payload.getUnusedData();
        String unusedDataJsonString = "";

        //unusedData to json string
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert Map to JSON String
            unusedDataJsonString= objectMapper.writeValueAsString(unusedData);

        } catch (Exception e) {
            e.printStackTrace(); // :(
        }

        return new RawDataLog(sensorDistance1, sensorDistance2, tiltState, temperature, humidity, comfort, unusedDataJsonString);

    }
}
