package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import be.kdg.magiwastebackend.payloadhandling.Payload;
import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import be.kdg.magiwastebackend.service.NotificationEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HTTPController {

    private final PayloadService payloadService;
    private final NotificationEventService notificationEventService;
    Logger logger = LoggerFactory.getLogger(HTTPController.class);


    private HTTPController(PayloadService payloadService, NotificationEventService notificationEventService) {
        this.payloadService = payloadService;
        this.notificationEventService = notificationEventService;
    }

    @PostMapping("/data")
    public ResponseEntity<String> test(@RequestBody Map<String, Object> body) {
        logger.debug("Received data: {}", body);

        try {
            Payload payload = cleanPayload(body);
            logger.debug("Cleaned payload: {}", payload);

            payloadService.processPayload(payload);

            if (payload.isTiltState()) {
                NotificationEvent notificationEvent = new NotificationEvent();
                notificationEvent.setNotificationMessage("Bin has been tilted");
                notificationEvent.setNotificationTime(LocalDateTime.now());
                notificationEventService.save(notificationEvent);
            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Error processing payload: {}", ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }//206 partial context //201 created resource //200 OK
    }

    //CLEANING HERE:
    Payload cleanPayload(Map<String, Object> body) {
        Map<String, Object> unusedData = new HashMap<>();
        Payload payload = new Payload();
        body.forEach((key, value) -> {
            try {
                setAttribute(payload, key, value);
            } catch (Exception e) {
                //no matching attribute :(
                unusedData.put(key, value);
                logger.warn("No matching attribute " + key + " in payload class");
                e.printStackTrace();
            }
        });
        payload.setUnusedData(unusedData);
        return payload;
    }

    private void setAttribute(Object obj, String attributeName, Object value) {
        try {
            // Get the class of the object
            Class<?> objClass = obj.getClass();

            // Get the field by name
            Field field = objClass.getDeclaredField(attributeName);

            // Make the field accessible if it's private
            field.setAccessible(true);

            // Set the value for the field
            field.set(obj, value);
        } catch (NoSuchFieldException e) {
            // Log the error when the field does not exist
            logger.warn("No matching attribute " + attributeName + " in payload class");
            // You can also add the field to the unusedData map or any other handling
        } catch (IllegalAccessException e) {
            // Handle any access control issues
            logger.error("Failed to set value for field " + attributeName, e);
        } catch (Exception e) {
            // Catch other unexpected exceptions
            logger.error("An unexpected error occurred while setting the attribute " + attributeName, e);
        }
    }
}