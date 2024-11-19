package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.stereotype.Component;

@Component
class PayloadEnricher implements PayloadHandler {

    public void handlePayload(Payload payload) {
        //TODO: use weather API to enrich payload.
    }

}
