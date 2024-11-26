package be.kdg.magiwastebackend.payloadhandling;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface PayloadService {
    void processPayload(Payload payload) throws JsonProcessingException;
}
