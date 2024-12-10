package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.WasteBin;

import java.util.List;

public interface WasteBinService {

    List<WasteBin> findAll();
    WasteBin save(WasteBin wasteBin);

    WasteBin findWasteBinById(Long binId);

    WasteBin findBinByDeviceId(String deviceId);
}
