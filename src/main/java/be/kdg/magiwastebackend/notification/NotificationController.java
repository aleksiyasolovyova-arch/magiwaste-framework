package be.kdg.magiwastebackend.notification;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.service.NotificationEventService;
import be.kdg.magiwastebackend.service.NotificationEventServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class NotificationController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final NotificationEventServiceImplementation notificationEventService;

    @Autowired
    public NotificationController(NotificationEventServiceImplementation notificationEventService) {
        this.notificationEventService = notificationEventService;
    }

    @GetMapping("/notifications")
    public SseEmitter subscribeNotifications() {
        System.out.println(emitters.size());
        SseEmitter emitter = new SseEmitter(100L);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        notificationEventService.findAll().forEach(notificationEvent -> {
            try {
                emitter.send(SseEmitter.event().name("TRASH").data(notificationEvent.getNotificationMessage()));
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    public void dispatchNotificationEvents(NotificationEvent notificationEvent) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("TRASH").data(notificationEvent.getNotificationMessage()));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });
        emitters.removeAll(deadEmitters);
    }

//    @GetMapping("/stream-sse")
//    public SseEmitter streamSseEvents(){
//        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
//        executor.execute(() -> {
//           try{
//               for (int i = 0; i < 10; i++){
//                   Thread.sleep(100);
//                   sseEmitter.send("SSE MVC-" + System.currentTimeMillis() + " count " + i);
//               }
//               sseEmitter.complete();
//           } catch (IOException | InterruptedException e) {
//               sseEmitter.completeWithError(e);
//           }
//        });
//        return sseEmitter;
//    }

}
