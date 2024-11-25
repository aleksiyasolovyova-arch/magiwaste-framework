package be.kdg.magiwastebackend.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDTO {

    @JsonProperty("hourly") // Map the "hourly" object in JSON to weather DTO
    private WeatherDTO weatherDTO;

    // Getters and Setters
    public WeatherDTO getHourly() {
        return weatherDTO;
    }

    public void setHourly(WeatherDTO hourly) {
        this.weatherDTO = hourly;
    }
}
