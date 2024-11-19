package be.kdg.magiwastebackend.web;

public class MapPoint {
    private double latitude;
    private double longitude;
    private String color;

    public MapPoint(double latitude, double longitude, String color) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.color = color;
    }

    public static String parseColor(double percentOfVolume){
        if (percentOfVolume > 90) return "red";
        if (percentOfVolume > 50) return "yellow";
        return "green";
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
