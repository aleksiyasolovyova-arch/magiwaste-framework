package be.kdg.magiwastebackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class NotificationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notifcationId;

    private String notificationMessage;
    private LocalDateTime notificationTime;


    public void setNotifcationId(Long notifcationId) {
        this.notifcationId = notifcationId;
    }

    public Long getNotifcationId() {
        return notifcationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public NotificationEvent(Long notifcationId, String notificationMessage, LocalDateTime notificationTime) {
        this.notifcationId = notifcationId;
        this.notificationMessage = notificationMessage;
        this.notificationTime = notificationTime;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    public NotificationEvent() {
    }

    @Override
    public String toString() {
        return "NotificationEvent{" +
                "notifcationId=" + notifcationId +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", notificationTime=" + notificationTime +
                '}';
    }
}
