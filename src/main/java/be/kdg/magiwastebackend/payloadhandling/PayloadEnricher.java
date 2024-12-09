package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.facade.ServiceFacade;
import be.kdg.magiwastebackend.domain.WeatherEvent;
import be.kdg.magiwastebackend.weatherapi.WeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
class PayloadEnricher implements PayloadHandler {

    private final RestTemplate restTemplate;
    private final ServiceFacade serviceFacade;

    PayloadEnricher(@Lazy RestTemplate restTemplate, ServiceFacade serviceFacade) {
        this.restTemplate = restTemplate;
        this.serviceFacade = serviceFacade;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public Payload handlePayload(Payload payload)  {

        ResponseEntity<String> weatherData = callWeatherApi(payload.getLatitude(), payload.getLongitude());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherDTO weatherDTO = objectMapper.readerFor(WeatherDTO.class).readValue(weatherData.getBody());
            WeatherEvent weather = new WeatherEvent(
                    weatherDTO.getTemperature(),
                    weatherDTO.getRelativeHumidity(),
                    weatherDTO.getApparentTemperature(),
                    weatherDTO.getPrecipitation(),
                    weatherDTO.getRain(),
                    weatherDTO.getShowers(),
                    weatherDTO.getSnowfall(),
                    weatherDTO.getWeatherCode(),
                    weatherDTO.getSurfacePressure(),
                    weatherDTO.getCloudCoverTotal(),
                    weatherDTO.getWindSpeed10m()
            );
            serviceFacade.saveWeatherEvent(weather);
            payload.setWeather(weather);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO replace with logger at some point --11/29/2024
        }

        return payload;
    }

    public ResponseEntity<String> callWeatherApi(double latitude, double longitude) {

        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,surface_pressure,wind_speed_10m", latitude, longitude);

        // GET request
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
