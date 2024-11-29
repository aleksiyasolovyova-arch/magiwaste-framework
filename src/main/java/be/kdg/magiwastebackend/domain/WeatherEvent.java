package be.kdg.magiwastebackend.domain;

import jakarta.persistence.*;

@Entity
public class WeatherEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double temperature;

    private int relativeHumidity;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double apparentTemperature;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double precipitation;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double rain;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double showers;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double snowfall;

    private int weatherCode;

    private String weatherCodeDescription;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double surfacePressure;

    private int cloudCoverTotal;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double windSpeed10m;


    public WeatherEvent(double temperature, int relativeHumidity, double apparentTemperature, double precipitation, double rain, double showers, double snowfall, int weatherCode, double surfacePressure, int cloudCoverTotal, double windSpeed10m) {
        this.temperature = temperature;
        this.relativeHumidity = relativeHumidity;
        this.apparentTemperature = apparentTemperature;
        this.precipitation = precipitation;
        this.rain = rain;
        this.showers = showers;
        this.snowfall = snowfall;
        this.weatherCode = weatherCode;
        setWeatherCodeDescription();
        this.surfacePressure = surfacePressure;
        this.cloudCoverTotal = cloudCoverTotal;
        this.windSpeed10m = windSpeed10m;
    }

    public WeatherEvent() {

    }


    private void setWeatherCodeDescription(){
//        Code	Description
//        0	            Clear sky
//        1, 2, 3	    Mainly clear, partly cloudy, and overcast
//        45, 48	    Fog and depositing rime fog
//        51, 53, 55	Drizzle: Light, moderate, and dense intensity
//        56, 57	    Freezing Drizzle: Light and dense intensity
//        61, 63, 65	Rain: Slight, moderate and heavy intensity
//        66, 67	    Freezing Rain: Light and heavy intensity
//        71, 73, 75	Snow fall: Slight, moderate, and heavy intensity
//        77	        Snow grains
//        80, 81, 82	Rain showers: Slight, moderate, and violent
//        85, 86	    Snow showers slight and heavy
//        95 *	        Thunderstorm: Slight or moderate
//        96, 99 *	    Thunderstorm with slight and heavy hail

        switch(weatherCode){
            case 0:
                weatherCodeDescription = "Clear sky";
                break;
            case 1:
                weatherCodeDescription = "Mainly clear";
                break;
            case 2:
                weatherCodeDescription = "partly cloudy";
                break;
            case 3:
                weatherCodeDescription = "overcast";
                break;
            case 45:
                weatherCodeDescription = "Fog";
                break;
            case 48:
                weatherCodeDescription = "depositing rime fog";
                break;
            case 51:
                weatherCodeDescription = "Drizzle: Light";
                break;
            case 53:
                weatherCodeDescription = "Drizzle: Moderate";
                break;
            case 55:
                weatherCodeDescription = "Drizzle: Dense";
                break;
            case 56:
                weatherCodeDescription = "Freezing Drizzle: Light";
                break;
            case 57:
                weatherCodeDescription = "Freezing Drizzle: Dense";
                break;
            case 61:
                weatherCodeDescription = "Rain: Slight";
                break;
            case 63:
                weatherCodeDescription = "Rain: Moderate";
                break;
            case 65:
                weatherCodeDescription = "Rain: Heavy";
                break;
            case 66:
                weatherCodeDescription = "Freezing Rain: Slight";
                break;
            case 67:
                weatherCodeDescription = "Freezing Rain: Heavy";
                break;
            case 71:
                weatherCodeDescription = "Snow fall: Slight";
                break;
            case 73:
                weatherCodeDescription = "Snow fall: Moderate";
                break;
            case 75:
                weatherCodeDescription = "Snow fall: Heavy";
                break;
            case 77:
                weatherCodeDescription = "Snow Grains";
                break;
            case 81:
                weatherCodeDescription = "Rain showers: Slight";
                break;
            case 83:
                weatherCodeDescription = "Rain showers: Moderate";
                break;
            case 85:
                weatherCodeDescription = "Rain showers: Heavy";
                break;
            case 86:
                weatherCodeDescription = "Snow showers: Slight";
                break;
            case 87:
                weatherCodeDescription = "Snow showers: Heavy";
                break;
            case 95:
                weatherCodeDescription = "Thunderstorm";
                break;
            case 96:
                weatherCodeDescription = "Thunderstorm: Slight Hail";
                break;
            case 99 :
                weatherCodeDescription = "Thunderstorm: Heavy Hail";
                break;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
