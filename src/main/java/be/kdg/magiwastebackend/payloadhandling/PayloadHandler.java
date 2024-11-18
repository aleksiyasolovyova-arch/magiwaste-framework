package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBinEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PayloadHandler {
    AlertMessageSystem alertMessageSystem;
    BinEventLogCreator binEventLogCreator;
    Payload payload;
    PayloadCleaner payloadCleaner;
    PayloadEnricher payloadEnricher;

    PayloadHandler(AlertMessageSystem alertMessageSystem, BinEventLogCreator binEventLogCreator, Payload payload, PayloadEnricher payloadEnricher, PayloadCleaner payloadCleaner) {
        this.alertMessageSystem = alertMessageSystem;
        this.binEventLogCreator = binEventLogCreator;
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

    WasteBinEvent createWasteBinEvent(Payload payload) {
        return AbstractWasteEventLogFactory.createWasteBinEvent(payload);
    }

//    WasteBinEvent cleanEnrichCreateSendPayload(HttpMessage httpMessage) {
//        Payload payload1 = payloadEnricher.enrichPayload(cleanPayload(httpMessage));
//        return createWasteBinEvent(payload1); //to be changed
//    }



}
