package be.kdg.magiwastebackend.payloadhandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
class PayloadServiceImplementation implements PayloadService {
    private final PayloadHandler payloadHandler;

    public PayloadServiceImplementation(@Qualifier("payloadProcessor") PayloadHandler payloadHandler) {
        this.payloadHandler= payloadHandler;
    }

    @Override
    public void processPayload(Payload payload) throws JsonProcessingException {
        payloadHandler.handlePayload(payload);
    }

    //we can add more methods here or call individual handlers through the payload processor
}
