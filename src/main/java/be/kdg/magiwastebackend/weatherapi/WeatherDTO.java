package be.kdg.magiwastebackend.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

    @JsonProperty("current")
    public void unpackNested(Map<String, Object> current){
        System.out.println(current);
        System.out.println(current.get("temperature_2m"));
        this.temperature = (double) current.get("temperature_2m");
        this.relativeHumidity = (int) current.get("relative_humidity_2m");
        this.apparentTemperature = (double) current.get("apparent_temperature");
        this.precipitation = (double) current.get("precipitation");
        this.rain = (double) current.get("rain");
        this.showers = (double) current.get("showers");
        this.snowfall = (double) current.get("snowfall");
        this.weatherCode = (int) current.get("weather_code");
        this.surfacePressure = (double) current.get("surface_pressure");
        this.cloudCoverTotal = (int) current.get("cloud_cover");
        this.windSpeed10m = (double) current.get("wind_speed_10m");
    }

    private LocalDateTime localDateTime;
    private double temperature;
    private int relativeHumidity;
    private double apparentTemperature;
    private double precipitation;
    private double rain;
    private double showers;
    private double snowfall;
    private int weatherCode;
    private double surfacePressure;
    private int cloudCoverTotal;
    private double windSpeed10m;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getRelativeHumidity() {
        return relativeHumidity;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getRain() {
        return rain;
    }

    public double getShowers() {
        return showers;
    }

    public double getSnowfall() {
        return snowfall;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public double getSurfacePressure() {
        return surfacePressure;
    }

    public int getCloudCoverTotal() {
        return cloudCoverTotal;
    }

    public double getWindSpeed10m() {
        return windSpeed10m;
    }
}
