package be.kdg.magiwastebackend.facade;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.weatherapi.WeatherEvent;

import java.util.List;

public interface ServiceFacade {
    List<WasteBin> findAllWasteBins();
    List<WasteBinEvent> findAllWasteBinEvents();
    List<RawDataLog> findAllDataLogs();
    List<WeatherEvent> findAllWeatherEvents();

    void saveWasteBin(WasteBin wasteBin);
    void saveWasteBinEvent(WasteBinEvent wasteBinEvent);
    void saveRawDataLog(RawDataLog rawDataLog);
    void saveWeatherEvent(WeatherEvent weatherEvent);

    WasteBin findWasteBinById(Long binId);
    WasteBin findBinByDeviceId(String deviceId);


}
