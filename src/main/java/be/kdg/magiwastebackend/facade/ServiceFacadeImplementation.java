package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.service.RawDataLogService;
import be.kdg.magiwastebackend.service.WasteBinEventService;
import be.kdg.magiwastebackend.service.WasteBinService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceFacadeImplementation implements ServiceFacade {

    private final WasteBinService wasteBinService;
    private final WasteBinEventService wasteBinEventService;
    private final RawDataLogService rawDataLogService;


    public ServiceFacadeImplementation(WasteBinService wasteBinService, WasteBinEventService wasteBinEventService, RawDataLogService rawDataLogService) {
        this.wasteBinService = wasteBinService;
        this.wasteBinEventService = wasteBinEventService;
        this.rawDataLogService = rawDataLogService;
    }

    @Override
    public List<WasteBin> findAllWasteBins() {
        return wasteBinService.findAll();
    }

    @Override
    public List<WasteBinEvent> findAllWasteBinEvents() {
        return wasteBinEventService.findAll();
    }

    @Override
    public List<RawDataLog> findAllDataLogs() {
        return rawDataLogService.findAll();
    }

    @Override
    public void saveWasteBin(WasteBin wasteBin) { wasteBinService.save(wasteBin); }

    @Override
    public void saveWasteBinEvent(WasteBinEvent wasteBinEvent) {
        wasteBinEventService.save(wasteBinEvent);
    }

    @Override
    public void saveRawDataLog(RawDataLog rawDataLog) {
        rawDataLogService.save(rawDataLog);
    }

    @Override
    public WasteBin findWasteBinById(Long binId) {
        return wasteBinService.findWasteBinById(binId);
    }

    @Override
    public WasteBin findBinByDeviceId(String deviceId) {
        return wasteBinService.findBinByDeviceId(deviceId);
    }


}
