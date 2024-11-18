package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBinEvent;

class BinEventLogCreator {
    AbstractWasteEventLogFactory wasteEventLogFactory;

    public BinEventLogCreator(AbstractWasteEventLogFactory wasteEventLogFactory) {
        this.wasteEventLogFactory = wasteEventLogFactory;
    }

    WasteBinEvent createWasteBinEvent(Payload payload) {
        return wasteEventLogFactory.createWasteBinEvent(payload);
    }
}
