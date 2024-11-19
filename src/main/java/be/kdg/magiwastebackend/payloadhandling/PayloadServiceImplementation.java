package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Service;

@Service
class PayloadServiceImplementation implements PayloadService {
    private final ServiceFacade serviceFacade;
    private final PayloadProcessor payloadProcessor;

    public PayloadServiceImplementation(ServiceFacade serviceFacade, PayloadProcessor payloadProcessor) {
        this.serviceFacade = serviceFacade;
        this.payloadProcessor = payloadProcessor;
    }

    @Override
    public void processPayload(Payload payload) {
        //TODO: actually enrich and check for alerts
        payloadProcessor.handlePayload(payload);

        //change this soon
        serviceFacade.saveRawDataLog(AbstractWasteEventLogFactory.createRawDataLog(payload));

        //with this too
        WasteBin bin = findBin(payload); //this logic should/ could be moved elsewhere
        serviceFacade.saveWasteBinEvent(AbstractWasteEventLogFactory.createWasteBinEvent(payload, bin));
    }

    @Override
    public WasteBin findBin(Payload payload){ //way to make this private?
        String deviceId = payload.getDeviceId();

        //get Bin associated with Payload
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
