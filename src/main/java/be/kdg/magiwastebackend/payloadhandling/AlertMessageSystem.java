package be.kdg.magiwastebackend.payloadhandling;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
class AlertMessageSystem implements PayloadHandler{
    private final ServiceFacade serviceFacade;

    AlertMessageSystem(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @Override
    public Payload handlePayload(Payload payload) {

        List<NotificationEvent> notificationEvents = serviceFacade.findAllNotificationEvents();

        if (!notificationEvents.isEmpty()) {
            if (payload.isTiltState()) {
                LocalDateTime now = LocalDateTime.now();

                NotificationEvent existingEvent = notificationEvents.stream()
                        .filter(event -> event.getNotificationTime().toLocalDate().isEqual(now.toLocalDate()) &&
                                event.getNotificationTime().toLocalTime().equals(now.toLocalTime()))
                        .findFirst()
                        .orElse(null);

                if (existingEvent != null) {
                    existingEvent.setNotificationMessage("This bin has been tilted at " + now);
                    serviceFacade.saveNotificationEvent(existingEvent);
                } else {
                    NotificationEvent notificationEvent = new NotificationEvent();
                    notificationEvent.setNotificationTime(now);
                    notificationEvent.setNotificationMessage("This bin has been tilted at " + now);

                    serviceFacade.saveNotificationEvent(notificationEvent);
                }
            }
        }

        return null;

    }
}
