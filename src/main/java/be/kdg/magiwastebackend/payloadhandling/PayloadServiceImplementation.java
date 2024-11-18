package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.http.HttpMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayloadServiceImplementation implements PayloadService {
    private final PayloadHandler payloadHandler;
    private final ServiceFacade serviceFacade;


    //method for all payload logic and clss delegation
    @Override
    public void doYourThing(Map<String, Object> body) {
        //create payload from rap map data
        Payload payload = payloadHandler.cleanPayload(body);

        //create a raw data log from the original information
        RawDataLog rawDataLog = AbstractWasteEventLogFactory.createRawDataLog(payload);
        serviceFacade.saveRawDataLog(rawDataLog);

        //enrich data here:
        payload = payloadHandler.enrichPayload(payload);

        //at this point here the data should be complete
        String deviceId = payload.getDeviceId();
        WasteBin bin = serviceFacade.findBinByDeviceId(deviceId);

        WasteBinEvent wasteBinEvent = payloadHandler.createWasteBinEvent(payload);
        wasteBinEvent.setBin(bin);
        serviceFacade.saveWasteBinEvent(wasteBinEvent);
    }

    public PayloadServiceImplementation(PayloadHandler payloadHandler, ServiceFacade serviceFacade) {
        this.payloadHandler = payloadHandler;
        this.serviceFacade = serviceFacade;
    }


    @Override
    public void getAndSendData(HttpMessage httpMessage){
//        WasteBinEvent wasteBinEvent = payloadHandler.cleanEnrichCreateSendPayload(httpMessage);
//        serviceFacade.saveWasteBinEvent(wasteBinEvent);
    }

    @Override
    public void getAndSendData(Long test) {

    }


}
