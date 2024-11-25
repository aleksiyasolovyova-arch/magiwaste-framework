package be.kdg.magiwastebackend.weatherapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherApiConfig {

    @Bean //Do NOT delete - Szymon
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
