package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PayloadHandler {
    AlertMessageSystem alertMessageSystem;
    Payload payload;
    PayloadCleaner payloadCleaner;
    PayloadEnricher payloadEnricher;

    PayloadHandler(AlertMessageSystem alertMessageSystem, Payload payload, PayloadEnricher payloadEnricher, PayloadCleaner payloadCleaner) {
        this.alertMessageSystem = alertMessageSystem;
        this.payload = payload;
        this.payloadEnricher = payloadEnricher;
        this.payloadCleaner = payloadCleaner;
    }

    Payload cleanPayload(Map<String, Object> body) {
        return payloadCleaner.cleanPayload(body);
    }

    Payload enrichPayload(Payload payload) {
        return payloadEnricher.enrichPayload(payload);
    }

    WasteBinEvent createWasteBinEvent(Payload payload, WasteBin bin) {
        return AbstractWasteEventLogFactory.createWasteBinEvent(payload, bin);
    }

//    WasteBinEvent cleanEnrichCreateSendPayload(HttpMessage httpMessage) {
//        Payload payload1 = payloadEnricher.enrichPayload(cleanPayload(httpMessage));
//        return createWasteBinEvent(payload1); //to be changed
//    }



}
