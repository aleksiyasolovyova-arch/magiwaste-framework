package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.stereotype.Service;

@Service
class PayloadServiceImplementation implements PayloadService {
    private final PayloadProcessor payloadProcessor;

    public PayloadServiceImplementation(PayloadProcessor payloadProcessor) {
        this.payloadProcessor = payloadProcessor;
    }

    @Override
    public void processPayload(Payload payload) {
        //TODO: actually enrich and check for alerts
        payloadProcessor.handlePayload(payload);
    }

    //we can add more methods here or call individual handlers through the payload processor
}
