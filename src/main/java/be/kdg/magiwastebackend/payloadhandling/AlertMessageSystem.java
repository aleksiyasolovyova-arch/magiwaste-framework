package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Component;

@Component
class AlertMessageSystem implements PayloadHandler{
    private ServiceFacade serviceFacade;

    @Override
    public void handlePayload(Payload payload) {

    }
}
