package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.payloadhandling.Payload;
import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HTTPController {

    private final PayloadService payloadService;
    Logger logger = LoggerFactory.getLogger(HTTPController.class);


    private HTTPController(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    @PostMapping("/data")
    public ResponseEntity<String> test(@RequestBody Map<String, Object> body) {
        logger.debug("received data {}", body);

        Payload payload = cleanPayload(body);
        payloadService.processPayload(payload);

        return new ResponseEntity<>(HttpStatus.CREATED);//206 partial context //201 created resource //200 OK
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

    //set the attribute of a class using the attribute name and stuff and values
    private void setAttribute(Object obj, String attributeName, Object value) throws Exception {
        // Get the class of the object
        Class<?> objClass = obj.getClass();

        // Get the field by name
        Field field = objClass.getDeclaredField(attributeName);

        // Make the field accessible if it's private
        field.setAccessible(true);

        // Set the value for the field
        field.set(obj, value);
    }
}