package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBinEvent;

class AbstractWasteEventLogFactory {
    WasteBinEvent createWasteBinEvent(Payload payload) {
       double sensorDistance1 = payload.getSensorDistance1();
       double sensorDistance2 = payload.getSensorDistance2();
       boolean tiltState = payload.isTiltState();
       String temperature = payload.getTemperature();

        return new WasteBinEvent(sensorDistance1, sensorDistance2, tiltState, temperature);
    }
}
