package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Component;

@Component
public class PayloadBinHandler {
    private final ServiceFacade serviceFacade;

    public PayloadBinHandler(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    WasteBin getBin(Payload payload){
        String deviceId = payload.getDeviceId();

        WasteBin bin = serviceFacade.findBinByDeviceId(deviceId);
        if (!payload.getAddress().isEmpty()){
            bin.setAddress(payload.getAddress());
        }
        if (payload.getLatitude() != 0) {
            bin.setLatitude(payload.getLatitude());
        }
        if (payload.getLongitude() != 0) {
            bin.setLongitude(payload.getLongitude());
        }

        return bin;
    }
}
