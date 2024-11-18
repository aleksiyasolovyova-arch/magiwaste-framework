package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import be.kdg.magiwastebackend.service.RawDataLogService;
import be.kdg.magiwastebackend.testing.SensorData;
import be.kdg.magiwastebackend.testing.SensorService;
import be.kdg.magiwastebackend.testing.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HTTPController {

    Logger logger = LoggerFactory.getLogger(TestController.class);
    private final SensorService sensorDataService;
    private final ServiceFacade serviceFacade;

    private HTTPController(SensorService sensorDataService, ServiceFacade serviceFacade) {
        this.sensorDataService = sensorDataService;
        this.serviceFacade = serviceFacade;
    }

    @PostMapping("/data")
    public ResponseEntity<SensorData> test(@RequestBody SensorData data) {
        logger.debug("received data {}", data);
        sensorDataService.addData(data);
        serviceFacade.saveRawDataLog(new RawDataLog(data.getSensorDistance1(), data.getSensorDistance2(), data.getTilted()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/data")
    public String showData(Model model) {
        model.addAttribute("datalists", serviceFacade.findAllDataLogs());
        return "data";
    }



}