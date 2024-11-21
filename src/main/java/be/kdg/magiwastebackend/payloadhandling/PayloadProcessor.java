package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
class PayloadProcessor implements PayloadHandler {
    private final PayloadEnricher payloadEnricher;
    private final AlertMessageSystem alertMessageSystem;
    private final EventAndLogSaver eventAndLogSaver;

    public PayloadProcessor(PayloadEnricher payloadEnricher, AlertMessageSystem alertMessageSystem, EventAndLogSaver eventAndLogSaver) {
        this.payloadEnricher = payloadEnricher;
        this.alertMessageSystem = alertMessageSystem;
        this.eventAndLogSaver = eventAndLogSaver;
    }

    @Override @Primary
    public Payload handlePayload(Payload payload) {
        Payload payloadEnriched = payloadEnricher.handlePayload(payload);
        alertMessageSystem.handlePayload(payloadEnriched);
        eventAndLogSaver.handlePayload(payloadEnriched);

        return null;
    }
}
