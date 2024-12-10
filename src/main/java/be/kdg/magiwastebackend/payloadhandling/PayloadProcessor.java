package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
class PayloadProcessor implements PayloadHandler {
    private final PayloadWeatherEnricher payloadWeatherEnricher;
    private final AlertMessageSystem alertMessageSystem;
    private final EventAndLogSaver eventAndLogSaver;

    public PayloadProcessor(PayloadWeatherEnricher payloadWeatherEnricher, AlertMessageSystem alertMessageSystem, EventAndLogSaver eventAndLogSaver) {
        this.payloadWeatherEnricher = payloadWeatherEnricher;
        this.alertMessageSystem = alertMessageSystem;
        this.eventAndLogSaver = eventAndLogSaver;
    }

    @Override
    @Primary
    public Payload handlePayload(Payload payload) {
        payload = payloadWeatherEnricher.handlePayload(payload);

        alertMessageSystem.handlePayload(payload);
        eventAndLogSaver.handlePayload(payload);

        return null;
    }
}
