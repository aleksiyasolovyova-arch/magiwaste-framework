package be.kdg.magiwastebackend.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class WeatherDTO {

    private LocalDateTime localDateTime;

    @JsonProperty("temperature_2m")
    private double[] temperature;

    @JsonProperty("relative_humidity_2m")
    private int[] relativeHumidity;

    @JsonProperty("apparent_temperature")
    private double[] apparentTemperature;

    @JsonProperty("precipitation")
    private double[] precipitation;

    @JsonProperty("rain")
    private double[] rain;

    @JsonProperty("showers")
    private double[] showers;

    @JsonProperty("snowfall")
    private double[] snowfall;

    @JsonProperty("weather_code")
    private int[] weatherCode;

    @JsonProperty("surface_pressure")
    private double[] surfacePressure;

    @JsonProperty("cloud_cover")
    private int[] cloudCoverTotal;

    @JsonProperty("wind_speed_10m")
    private double[] windSpeed10m;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public int[] getRelativeHumidity() {
        return relativeHumidity;
    }

    public double[] getApparentTemperature() {
        return apparentTemperature;
    }

    public double[] getPrecipitation() {
        return precipitation;
    }

    public double[] getRain() {
        return rain;
    }

    public double[] getShowers() {
        return showers;
    }

    public double[] getSnowfall() {
        return snowfall;
    }

    public int[] getWeatherCode() {
        return weatherCode;
    }

    public double[] getSurfacePressure() {
        return surfacePressure;
    }

    public int[] getCloudCoverTotal() {
        return cloudCoverTotal;
    }

    public double[] getWindSpeed10m() {
        return windSpeed10m;
    }
}
