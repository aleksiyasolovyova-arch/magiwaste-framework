package be.kdg.magiwastebackend.notification;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.service.NotificationEventService;
import be.kdg.magiwastebackend.service.NotificationEventServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Controller
public class NotificationController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final NotificationEventService notificationEventService;

    @Autowired
    public NotificationController(NotificationEventServiceImplementation notificationEventService) {
        this.notificationEventService = notificationEventService;
    }

    @GetMapping("/notifications")
    public String getNotificationsPage(Model model) {
        List<NotificationEvent> notifications = notificationEventService.findAll();

        NotificationEvent latestNotification = notifications.isEmpty() ? null : notifications.get(notifications.size() - 1);
        String latestNotificationTime = latestNotification != null ? latestNotification.getNotificationTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString() : null;

        model.addAttribute("latestNotificationTime", latestNotificationTime);
        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    @RequestMapping(value = "/notifications-stream", method = RequestMethod.GET)
    public SseEmitter subscribeNotifications() {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                List<NotificationEvent> notifications = notificationEventService.findAll();
                if (!notifications.isEmpty()) {
                    NotificationEvent latestNotification = notifications.get(notifications.size() - 1);
                    String notificationMessage = latestNotification.getNotificationMessage();
                    emitter.send(SseEmitter.event().name("notification").data(notificationMessage));
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }, 0, 5, TimeUnit.SECONDS);

        return emitter;
    }

}
