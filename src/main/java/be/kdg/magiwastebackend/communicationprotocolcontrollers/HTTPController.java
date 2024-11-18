package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import org.springframework.http.HttpMessage;

public class HTTPController {
    PayloadService payloadService;


    private HTTPController(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    public void getAndSendData(HttpMessage httpMessage){
        payloadService.getAndSendData(httpMessage);
    }
}
