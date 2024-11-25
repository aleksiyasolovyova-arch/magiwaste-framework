package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Component;

@Component
class EventAndLogSaver implements PayloadHandler{
    private final ServiceFacade serviceFacade;

    EventAndLogSaver(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public Payload handlePayload(Payload payload) {

        String deviceId = payload.getDeviceId();
        //get Bin associated with Payload
        WasteBin bin = serviceFacade.findBinByDeviceId(deviceId);
        if (payload.getAddress()!=null && !payload.getAddress().isEmpty()){
            bin.setAddress(payload.getAddress());
        }
        if (payload.getLatitude() != 0) {
            bin.setLatitude(payload.getLatitude());
        }
        if (payload.getLongitude() != 0) {
            bin.setLongitude(payload.getLongitude());
        }

        serviceFacade.saveRawDataLog(AbstractWasteEventLogFactory.createRawDataLog(payload));
        serviceFacade.saveWasteBinEvent(AbstractWasteEventLogFactory.createWasteBinEvent(payload, bin));

        return null;
    }
}
