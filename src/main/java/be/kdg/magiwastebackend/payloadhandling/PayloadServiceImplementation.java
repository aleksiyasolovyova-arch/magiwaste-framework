package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.http.HttpMessage;

//@Service //TODO: wire this
public class PayloadServiceImplementation implements PayloadService {
    private final PayloadHandler payloadHandler;
    private final ServiceFacade serviceFacade;

    public PayloadServiceImplementation(PayloadHandler payloadHandler, ServiceFacade serviceFacade) {
        this.payloadHandler = payloadHandler;
        this.serviceFacade = serviceFacade;
    }


    @Override
    public void getAndSendData(HttpMessage httpMessage){
        WasteBinEvent wasteBinEvent = payloadHandler.cleanEnrichCreateSendPayload(httpMessage);
        serviceFacade.saveWasteBinEvent(wasteBinEvent);
    }

    @Override
    public void getAndSendData(Long test) {

    }
}
