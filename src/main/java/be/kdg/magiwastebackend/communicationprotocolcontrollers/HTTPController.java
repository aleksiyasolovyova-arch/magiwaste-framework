package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import be.kdg.magiwastebackend.payloadhandling.Payload;
import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import be.kdg.magiwastebackend.testing.SensorService;
import be.kdg.magiwastebackend.testing.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class HTTPController {

    Logger logger = LoggerFactory.getLogger(HTTPController.class);
    private final ServiceFacade serviceFacade;
    private final PayloadService payloadService;

    private HTTPController(ServiceFacade serviceFacade, PayloadService payloadService) {
        this.serviceFacade = serviceFacade;
        this.payloadService = payloadService;
    }

    @PostMapping("/data")
    public ResponseEntity<String> test(@RequestBody Map<String, Object> body) {

        logger.debug("received data {}", body);

        //payload service does its thing
        payloadService.doYourThing(body);

        //206 partial context
        //201 created resource
        //200 OK
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/data")
    public String showData(Model model) {
        model.addAttribute("datalists", serviceFacade.findAllDataLogs());
        return "data";
    }



}