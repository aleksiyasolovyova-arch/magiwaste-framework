package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.NotificationEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

public interface NotificationEventService {
    List<NotificationEvent> findAll();

    NotificationEvent save(NotificationEvent notificationEvent);


}
