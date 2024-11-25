package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.facade.ServiceFacade;
import be.kdg.magiwastebackend.weatherapi.WeatherEvent;
import be.kdg.magiwastebackend.weatherapi.WeatherDTO;
import be.kdg.magiwastebackend.weatherapi.WeatherFactory;
import be.kdg.magiwastebackend.weatherapi.WeatherResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
class PayloadEnricher implements PayloadHandler {

    private final RestTemplate restTemplate;
    private final ServiceFacade serviceFacade;

    PayloadEnricher(RestTemplate restTemplate, ServiceFacade serviceFacade) {
        this.restTemplate = restTemplate;
        this.serviceFacade = serviceFacade;
    }

    public Payload handlePayload(Payload payload) throws JsonProcessingException {
        //TODO: use weather API to enrich payload.

        ResponseEntity<String> weatherData = callWeatherApi(payload.getLatitude(), payload.getLongitude());

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDTO weatherDTO =  objectMapper.readValue(weatherData.getBody(), WeatherResponseDTO.class).getHourly();
        WeatherEvent weather = WeatherFactory.createWeatherEvent(weatherDTO);
        serviceFacade.saveWeatherEvent(weather);
        payload.setWeather(weather);


        return payload;
    }

    public ResponseEntity<String> callWeatherApi(double latitude, double longitude) {

        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,surface_pressure,cloud_cover,wind_speed_10m&forecast_days=1", latitude, longitude);

        // GET request
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
