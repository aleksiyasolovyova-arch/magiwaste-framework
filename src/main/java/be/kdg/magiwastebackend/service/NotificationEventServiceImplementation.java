package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.repository.NotificationEventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationEventServiceImplementation implements NotificationEventService {

    private final NotificationEventRepository notificationEventRepository;

    public NotificationEventServiceImplementation(NotificationEventRepository notificationEventRepository) {
        this.notificationEventRepository = notificationEventRepository;
    }

    @Override
    public List<NotificationEvent> findAll() {
        return notificationEventRepository.findAll();
    }

    @Override
    public NotificationEvent save(NotificationEvent notificationEvent) {
        return notificationEventRepository.save(notificationEvent);
    }

}
