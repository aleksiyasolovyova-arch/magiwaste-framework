package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.*;

import java.util.List;

public interface ServiceFacade {
    List<WasteBin> findAllWasteBins();
    List<WasteBinEvent> findAllWasteBinEvents();
    List<RawDataLog> findAllDataLogs();
    List<WeatherEvent> findAllWeatherEvents();
    List<NotificationEvent> findAllNotificationEvents();

    void saveWasteBin(WasteBin wasteBin);
    void saveWasteBinEvent(WasteBinEvent wasteBinEvent);
    void saveRawDataLog(RawDataLog rawDataLog);
    void saveWeatherEvent(WeatherEvent weatherEvent);

    WasteBin findWasteBinById(Long binId);
    WasteBin findBinByDeviceId(String deviceId);


    List<WasteBinEvent> findAllWasteBinEventsByWasteBin(WasteBin bin);
}
