package be.kdg.magiwastebackend.web;

public class MapPoint {
    private double latitude;
    private double longitude;
    private String color;
    private int percentOfVolume;
    private String address;


    public MapPoint(double latitude, double longitude, int percentOfVolume, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.color = parseColor(percentOfVolume);
        this.percentOfVolume = percentOfVolume;
        this.address = address;
    }

    public String parseColor(double percentOfVolume){
        if (percentOfVolume > 90) return "red";
        if (percentOfVolume > 70) return "yellow";
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

    public int getPercentOfVolume() {
        return percentOfVolume;
    }

    public void setPercentOfVolume(int percentOfVolume) {
        this.percentOfVolume = percentOfVolume;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
