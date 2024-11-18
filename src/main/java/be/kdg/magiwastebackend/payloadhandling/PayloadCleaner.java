package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.http.HttpMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;

class PayloadCleaner {
    Payload payload;

    public PayloadCleaner(Payload payload) {
        this.payload = payload;
    }

    Payload cleanPayload(@RequestBody HttpMessage httpMessage) {
        return new Payload(); //TODO: URGENT actually don't have an empty payload
    }
}
