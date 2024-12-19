package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.*;
import be.kdg.magiwastebackend.service.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceFacadeImplementation implements ServiceFacade {

    private final WasteBinService wasteBinService;
    private final WasteBinEventService wasteBinEventService;
    private final RawDataLogService rawDataLogService;
    private final WeatherEventService weatherEventService;
    private final NotificationEventService notificationEventService;


    public ServiceFacadeImplementation(WasteBinService wasteBinService, WasteBinEventService wasteBinEventService, RawDataLogService rawDataLogService, WeatherEventService weatherEventService, NotificationEventService notificationEventService) {
        this.wasteBinService = wasteBinService;
        this.wasteBinEventService = wasteBinEventService;
        this.rawDataLogService = rawDataLogService;
        this.weatherEventService = weatherEventService;
        this.notificationEventService = notificationEventService;
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
    public List<WeatherEvent> findAllWeatherEvents() {
        return weatherEventService.findAll();
    }

    @Override
    public List<NotificationEvent> findAllNotificationEvents() {
        return notificationEventService.findAll();
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
    public void saveWeatherEvent(WeatherEvent weatherEvent) {
        weatherEventService.saveWeatherEvent(weatherEvent);
    }

    @Override
    public void saveNotificationEvent(NotificationEvent notificationEvent) {
            notificationEventService.save(notificationEvent);
    }

    @Override
    public WasteBin findWasteBinById(Long binId) {
        return wasteBinService.findWasteBinById(binId);
    }

    @Override
    public WasteBin findBinByDeviceId(String deviceId) {
        return wasteBinService.findBinByDeviceId(deviceId);
    }

    @Override
    public List<WasteBinEvent> findAllWasteBinEventsByWasteBin(WasteBin bin) {
        return wasteBinEventService.findAllByBin(bin);
    }


}
