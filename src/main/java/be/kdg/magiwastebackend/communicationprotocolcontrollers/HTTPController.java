package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.facade.ServiceFacade;
import be.kdg.magiwastebackend.facade.ServiceFacadeImplementation;
import be.kdg.magiwastebackend.payloadhandling.Payload;
import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import be.kdg.magiwastebackend.service.WasteBinEventServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HTTPController {

    private final PayloadService payloadService;
    Logger logger = LoggerFactory.getLogger(HTTPController.class);
    private  final ServiceFacade serviceFacade;

    private HTTPController(PayloadService payloadService, ServiceFacade serviceFacade) {
        this.payloadService = payloadService;
        this.serviceFacade = serviceFacade;
    }

    @PostMapping("/data")
    public ResponseEntity<String> test(@RequestBody Map<String, Object> body) {
        logger.debug("received data {}", body);

        Payload payload = cleanPayload(body);
        payloadService.processPayload(payload);

        return new ResponseEntity<>(HttpStatus.CREATED);//206 partial context //201 created resource //200 OK
    }

    @GetMapping("/data") //todo: should be removed
    public String showData(Model model) {
        model.addAttribute("datalists", serviceFacade.findAllDataLogs());
        return "data";
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