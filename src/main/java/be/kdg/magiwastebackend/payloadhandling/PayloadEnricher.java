package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.stereotype.Component;

@Component
class PayloadEnricher implements PayloadHandler {

    public Payload handlePayload(Payload payload) {

        //TODO: use weather API to enrich payload.

        return payload;
    }

}
