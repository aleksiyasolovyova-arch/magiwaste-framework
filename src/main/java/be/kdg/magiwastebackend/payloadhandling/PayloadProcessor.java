package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.stereotype.Component;

@Component
class PayloadProcessor implements PayloadHandler {
    private final PayloadEnricher payloadEnricher;
    private final AlertMessageSystem alertMessageSystem;

    public PayloadProcessor(PayloadEnricher payloadEnricher, AlertMessageSystem alertMessageSystem) {
        this.payloadEnricher = payloadEnricher;
        this.alertMessageSystem = alertMessageSystem;
    }


    @Override
    public void handlePayload(Payload payload) {
        payloadEnricher.handlePayload(payload);
        alertMessageSystem.handlePayload(payload);

    }
}
