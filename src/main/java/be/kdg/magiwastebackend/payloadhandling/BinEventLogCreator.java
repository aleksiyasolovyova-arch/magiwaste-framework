//package be.kdg.magiwastebackend.payloadhandling;
//
//import be.kdg.magiwastebackend.domain.WasteBin;
//import be.kdg.magiwastebackend.facade.ServiceFacade;
//import org.springframework.stereotype.Component;
//
//@Component
//class BinEventLogCreator implements PayloadHandler{
//    private final ServiceFacade serviceFacade;
//
//    BinEventLogCreator(ServiceFacade serviceFacade) {
//        this.serviceFacade = serviceFacade;
//    }
//
//    @Override
//    public void handlePayload(Payload payload) {
//        String deviceId = payload.getDeviceId();
//
//        //get Bin associated with Payload //TODO: see if this function is better placed in a different implementation of PayloadHandler.
//        WasteBin bin = serviceFacade.findBinByDeviceId(deviceId);
//        if (!payload.getAddress().isEmpty()){
//            bin.setAddress(payload.getAddress());
//        }
//        if (payload.getLatitude() != 0) {
//            bin.setLatitude(payload.getLatitude());
//        }
//        if (payload.getLongitude() != 0) {
//            bin.setLongitude(payload.getLongitude());
//        }
//
//        //create new event
//        //return AbstractWasteEventLogFactory.createWasteBinEvent(payload, bin);
//        //IF RETURNED, NO DEPENDENCY
//
//        //create raw log of event
//        //return AbstractWasteEventLogFactory.createRawDataLog(payload);
//        //IF RETURNED INSTEAD OF CREATED, NO DEPENDENCY
//
//        //TODO output?
//
//    }
//
//
//}
