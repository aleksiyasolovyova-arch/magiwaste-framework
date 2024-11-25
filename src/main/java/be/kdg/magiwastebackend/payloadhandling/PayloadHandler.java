package be.kdg.magiwastebackend.payloadhandling;

import com.fasterxml.jackson.core.JsonProcessingException;

interface PayloadHandler {

    Payload handlePayload(Payload payload) throws JsonProcessingException;

}
