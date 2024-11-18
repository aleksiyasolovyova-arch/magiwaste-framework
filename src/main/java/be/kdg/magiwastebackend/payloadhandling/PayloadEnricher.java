package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.stereotype.Component;

@Component
class PayloadEnricher {
    PayloadEnricher() {}

    public static Payload enrichPayloadWithWeatherData(Payload payload) {
        //TODO: URGENT use weather API to enrich payload.
        return payload;
    }

    Payload enrichPayload(Payload payload) {
        return payload; //TODO: URGENT use weather API to enrich payload.
    }
}
