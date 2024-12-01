package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
class AbstractWasteEventLogFactory {

    static WasteBinEvent createWasteBinEvent(Payload payload, WasteBin bin) {
        double sensorDistance1 = payload.getSensorDistance1();
        double sensorDistance2 = payload.getSensorDistance2();
        boolean tiltState = payload.isTiltState();
        double temperature = payload.getTemperature();
        double humidity = payload.getHumidity();
        String comfort = payload.getComfort();

        //get the fullness of the bin
        double percentOfVolume = (bin.getBinType().getHeight() - ((sensorDistance1 + sensorDistance2)/ 2))  / bin.getBinType().getHeight() * 100;
        boolean isFull = percentOfVolume > 85;

        WasteBinEvent event = new WasteBinEvent();
        event.setBin(bin);
        event.setSensorDistance1(sensorDistance1);
        event.setSensorDistance2(sensorDistance2);
        event.setTiltState(tiltState);
        event.setTemperature(temperature);
        event.setHumidity(humidity);
        event.setPercentOfVolume(percentOfVolume);
        event.setComfort(comfort); //TODO replace with enums
        event.setEventDate(LocalDateTime.now());
        event.setFull(isFull);

        event.setWeatherEvent(payload.getWeather());

        return event;
    }

    static RawDataLog createRawDataLog(Payload payload) {
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
            unusedDataJsonString = objectMapper.writeValueAsString(unusedData);

        } catch (Exception e) {
            e.printStackTrace(); // :(
        }

        return new RawDataLog(sensorDistance1, sensorDistance2, tiltState, temperature, humidity, comfort, unusedDataJsonString);

    }
}
