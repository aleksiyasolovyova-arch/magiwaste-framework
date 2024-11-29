package be.kdg.magiwastebackend.weatherapi;

import be.kdg.magiwastebackend.domain.WeatherEvent;

import java.time.LocalDateTime;

public class WeatherFactory {

    public static WeatherEvent createWeatherEvent(WeatherDTO weatherDTO, LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        WeatherEvent weatherObject = new WeatherEvent(
                weatherDTO.getTemperature()[hour],
                weatherDTO.getRelativeHumidity()[hour],
                weatherDTO.getApparentTemperature()[hour],
                weatherDTO.getPrecipitation()[hour],
                weatherDTO.getRain()[hour],
                weatherDTO.getShowers()[hour],
                weatherDTO.getSnowfall()[hour],
                weatherDTO.getWeatherCode()[hour],
                weatherDTO.getSurfacePressure()[hour],
                weatherDTO.getCloudCoverTotal()[hour],
                weatherDTO.getWindSpeed10m()[hour]
        );

        return weatherObject;
    }


    public static WeatherEvent createWeatherEvent(WeatherDTO weatherDTO) {
        return createWeatherEvent(weatherDTO, LocalDateTime.now());
    }

}
