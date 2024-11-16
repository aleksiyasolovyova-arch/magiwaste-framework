package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.payloadhandling.Payload;
import be.kdg.magiwastebackend.payloadhandling.PayloadHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class PayloadServiceImplementation implements PayloadService {
    private PayloadHandler payloadHandler;

    public PayloadServiceImplementation(PayloadHandler payloadHandler) {
        this.payloadHandler = payloadHandler;
    }




}
