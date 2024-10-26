package be.kdg.magiwastebackend.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/data")
    public ResponseEntity<String> test(@RequestBody SensorData data) {
        logger.debug("received data {}", data);

        return new ResponseEntity<>("Data Received Succesfully", HttpStatus.OK);
    }
}
