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
            NotificationEvent notificationEvent = notificationEvents.get(0);
            if (payload.isTiltState()) {
                notificationEvent.setNotificationTime(LocalDateTime.now());
                notificationEvent.setNotificationMessage("This bin has been tilted at " + notificationEvent.getNotificationTime().format(DateTimeFormatter.ofPattern("HH:mm")) );
            }
            serviceFacade.saveNotificationEvent(notificationEvent);
        }

        return null;

    }
}
