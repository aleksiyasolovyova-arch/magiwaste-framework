package be.kdg.magiwastebackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WasteBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String deviceId;

    String address;
    double longitude;
    double latitude;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bin")
    List<WasteBinEvent> events;

    public WasteBin() {
    }

    public WasteBin(String address, double longitude, double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public WasteBin(String address, double longitude, double latitude, List<WasteBinEvent> events) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.events = events;
    }

    public WasteBin(Long id, String address, double longitude, double latitude, List<WasteBinEvent> events) {
        this.id = id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.events = events;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<WasteBinEvent> getEvents() {
        return events;
    }

    public void setEvents(List<WasteBinEvent> events) {
        this.events = events;
    }
}
