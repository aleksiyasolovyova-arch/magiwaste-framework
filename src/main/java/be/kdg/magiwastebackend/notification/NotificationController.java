package be.kdg.magiwastebackend.notification;

import be.kdg.magiwastebackend.service.NotificationEventService;
import be.kdg.magiwastebackend.service.NotificationEventServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class NotificationController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final NotificationEventService notificationEventService;

    @Autowired
    public NotificationController(NotificationEventServiceImplementation notificationEventService) {
        this.notificationEventService = notificationEventService;
    }

    @GetMapping("/notifications")
    public SseEmitter subscribeNotifications() {
        SseEmitter emitter = new SseEmitter(100L);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        notificationEventService.findAll().forEach(notificationEvent -> {
            try {
                emitter.send(SseEmitter.event().name(notificationEvent.getNotificationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).data(notificationEvent.getNotificationMessage()));
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
