package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;

import java.util.List;

public interface ServiceFacade {
    List<WasteBin> findAllWasteBins();
    List<WasteBinEvent> findAllWasteBinEvents();
    List<RawDataLog> findAllDataLogs();

    void saveWasteBin(WasteBin wasteBin);
    void saveWasteBinEvent(WasteBinEvent wasteBinEvent);
    void saveRawDataLog(RawDataLog rawDataLog);

    WasteBin findWasteBinById(Long binId);

    WasteBin findBinByDeviceId(String deviceId);
}
