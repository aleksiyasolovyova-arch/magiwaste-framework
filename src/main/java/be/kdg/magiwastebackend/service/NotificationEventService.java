package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import java.util.List;

public interface NotificationEventService {
    List<NotificationEvent> findAll();

    NotificationEvent save(NotificationEvent notificationEvent);
}
