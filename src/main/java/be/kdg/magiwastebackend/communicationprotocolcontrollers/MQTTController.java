package be.kdg.magiwastebackend.communicationprotocolcontrollers;

import be.kdg.magiwastebackend.payloadhandling.Payload;
import be.kdg.magiwastebackend.payloadhandling.PayloadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("data2")
public class MQTTController {

    Logger logger = LoggerFactory.getLogger(MQTTController.class);
    private final PayloadService payloadService;
    private final String topic = "mqtt-topic";
    private final String userName = "admin";
    private final String password = "initial01";


    private MQTTController(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    @Bean
    public MessageChannel mqqtInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound(MessageChannel mqqtInputChannel) {

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setCleanSession(true);

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://10.134.178.158:1883", "HJVU",topic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0);
        adapter.setOutputChannel(mqqtInputChannel);
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqqtInputChannel")
    public MessageHandler handler() {
        return message -> {
            try {
                logger.debug("Message received: " + message.getPayload());
                Map<String, Object> body = parsePayload(message.getPayload());
                Payload payload = cleanPayload(body);
                payloadService.processPayload(payload);

                logger.debug("Payload processed: " + payload);
            }catch (Exception e) {
                logger.error("Error processing payload", e);
            }
        };
    }

    private Map<String, Object> parsePayload(Object rawPayload) throws Exception {
        return new ObjectMapper().readValue(rawPayload.toString(), HashMap.class);
    }


//see HTTP controller clas for explanation
    Payload cleanPayload(Map<String, Object> body) {
        Map<String, Object> unusedData = new HashMap<>();
        Payload payload = new Payload();
        body.forEach((key, value) -> {
            try {
                setAttribute(payload, key, value);
            } catch (Exception e) {
                unusedData.put(key, value);
                logger.warn("No matching attribute " + key + " in payload class");
                e.printStackTrace();
            }
        });
        payload.setUnusedData(unusedData);
        return payload;
    }

    //see HTPP Controller for explanation
    private void setAttribute(Object obj, String attributeName, Object value) throws Exception {
        Class<?> objClass = obj.getClass();

        Field field = objClass.getDeclaredField(attributeName);

        field.setAccessible(true);

        field.set(obj, value);
    }

}
