package be.kdg.magiwastebackend.web;

import be.kdg.magiwastebackend.domain.AppUser;
import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.service.NotificationEventService;
import be.kdg.magiwastebackend.service.NotificationEventServiceImplementation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
    public String getNotificationsPage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");
        boolean userLoggedOut;
        userLoggedOut = user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"));
        model.addAttribute("isUserLoggedIn", !userLoggedOut);

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

        final String[] lastSentNotificationId = {null};

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                List<NotificationEvent> notifications = notificationEventService.findAll();
                if (!notifications.isEmpty()) {
                    NotificationEvent latestNotification = notifications.get(notifications.size() - 1);
                    String latestNotificationId = latestNotification.getNotificationId().toString();

                    if (!latestNotificationId.equals(lastSentNotificationId[0])) {
                        String notificationMessage = latestNotification.getNotificationMessage();
                        emitter.send(SseEmitter.event().name("notification").data(notificationMessage));

                        lastSentNotificationId[0] = latestNotificationId;
                    }
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }, 0, 5, TimeUnit.SECONDS);

        return emitter;
    }


}
