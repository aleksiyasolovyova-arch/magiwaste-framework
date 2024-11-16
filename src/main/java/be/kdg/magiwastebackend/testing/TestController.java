package be.kdg.magiwastebackend.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);
    SensorService sensorDataService;
    public TestController(SensorService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping("/data")
    public ResponseEntity<SensorData> test(@RequestBody SensorData data) {
        logger.debug("received data {}", data);
        sensorDataService.addData(data);
        return new ResponseEntity<SensorData>( HttpStatus.OK);
    }



    @GetMapping("/data")
    public String showData(Model model){
        model.addAttribute("datalists",sensorDataService.getSensorData());
        return "data";
    }
}
