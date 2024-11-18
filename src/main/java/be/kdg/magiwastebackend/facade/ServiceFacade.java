package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.service.RawDataLogService;

import java.util.List;

public interface ServiceFacade {
    List<WasteBin> findAllWasteBins();
    List<WasteBinEvent> findAllWasteBinEvents();
    List<RawDataLog> findAllDataLogs();

    WasteBin saveWasteBin(WasteBin wasteBin);
    WasteBinEvent saveWasteBinEvent(WasteBinEvent wasteBinEvent);
    RawDataLog saveRawDataLog(RawDataLog rawDataLog);

    WasteBin findWasteBinById(Long binId);

    WasteBin findBinByDeviceId(String deviceId);
}
