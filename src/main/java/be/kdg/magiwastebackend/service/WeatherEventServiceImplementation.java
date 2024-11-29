package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.repository.WeatherEventRepository;
import be.kdg.magiwastebackend.domain.WeatherEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherEventServiceImplementation implements WeatherEventService{

    private final WeatherEventRepository weatherEventRepository;

    public WeatherEventServiceImplementation(WeatherEventRepository weatherEventRepository) {
        this.weatherEventRepository = weatherEventRepository;
    }

    @Override
    public WeatherEvent saveWeatherEvent(WeatherEvent weatherEvent) {
        return weatherEventRepository.save(weatherEvent);
    }

    @Override
    public List<WeatherEvent> findAll() {
        return weatherEventRepository.findAll();
    }
}
