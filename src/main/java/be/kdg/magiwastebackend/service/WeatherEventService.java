package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.weatherapi.WeatherEvent;

import java.util.List;

public interface WeatherEventService {
    WeatherEvent saveWeatherEvent(WeatherEvent weatherEvent);
    List<WeatherEvent> findAll();
}
