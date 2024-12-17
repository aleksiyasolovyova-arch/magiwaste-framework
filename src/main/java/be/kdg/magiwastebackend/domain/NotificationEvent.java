package be.kdg.magiwastebackend.domain;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
public class NotificationEvent {
    @Id
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "notification_message", nullable = false, length = 255)
    private String notificationMessage;

    @Column(name = "notification_time")
    private LocalDateTime notificationTime;


    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public NotificationEvent( String notificationMessage, LocalDateTime notificationTime) {
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
                "notifcationId=" + notificationId +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", notificationTime=" + notificationTime +
                '}';
    }
}
