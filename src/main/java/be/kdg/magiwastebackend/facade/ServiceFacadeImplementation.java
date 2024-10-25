package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.service.RawDataLogService;
import be.kdg.magiwastebackend.service.WasteBinEventService;
import be.kdg.magiwastebackend.service.WasteBinService;

import java.util.List;

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
    public WasteBin saveWasteBin(WasteBin wasteBin) {
        return wasteBinService.save(wasteBin);
    }

    @Override
    public WasteBinEvent saveWasteBinEvent(WasteBinEvent wasteBinEvent) {
        return wasteBinEventService.save(wasteBinEvent);
    }

    @Override
    public RawDataLog saveRawDataLog(RawDataLog rawDataLog) {
        return rawDataLogService.save(rawDataLog);
    }


}
