package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.payloadhandling.Payload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class MQTTController {

    Logger logger = LoggerFactory.getLogger(MQTTController.class);
    private final String topic = "mqtt-topic";
    private final String userName = "admin";
    private final String password = "initial01";
    private Payload payload;






    //see HTTP controller clas for explanation


}
