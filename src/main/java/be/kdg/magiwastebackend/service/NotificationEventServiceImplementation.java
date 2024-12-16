package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.notification.NotificationController;
import be.kdg.magiwastebackend.repository.NotificationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class NotificationEventServiceImplementation implements NotificationEventService {

    private final List<Consumer<NotificationEvent>> listeners = new ArrayList<>();
    private final NotificationEventRepository notificationEventRepository;
    private NotificationController notificationController;

    @Autowired
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
