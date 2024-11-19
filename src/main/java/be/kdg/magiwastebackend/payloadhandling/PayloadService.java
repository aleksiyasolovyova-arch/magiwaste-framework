package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.WasteBin;

public interface PayloadService {

    void processPayload(Payload payload);
    WasteBin findBin(Payload payload); //todo: move this?

}
