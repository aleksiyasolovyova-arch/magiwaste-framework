package be.kdg.magiwastebackend.mqtt;

import be.kdg.magiwastebackend.payloadhandling.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.*;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.*;
import java.lang.reflect.Field;
import java.util.*;

@Configuration
public class MqttConfig {

    private final PayloadService payloadService;
    private MqttClient mqttClient;
    Logger logger = LoggerFactory.getLogger(MqttConfig.class);

    public MqttConfig(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{"tcp://10.134.178.158:1883"});
        options.setUserName("admin2");
        options.setPassword("initial01".toCharArray());
        options.setCleanSession(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageProducer inbound() {
        String clientId = "uniqueClientId-" + UUID.randomUUID();
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId,
                mqttClientFactory(),
                "test/topic", "mqtt-topic"
        );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        Logger logger = LoggerFactory.getLogger(MqttConfig.class);

        return message -> {
            logger.info("Received Payload: {}", message.getPayload());
            System.out.println(message.getPayload());
            try {
                Map<String, Object> body = parsePayloadToMap(message.getPayload().toString());
                Payload payload = cleanPayload(body);
                payloadService.processPayload(payload);
            } catch (Exception e) {
                logger.error("Error processing MQTT message", e);
            }
            logger.info("Headers: {}", message.getHeaders());
            logger.info("Topic: {}", message.getHeaders().get("mqtt_receivedTopic"));
            logger.info("QoS: {}", message.getHeaders().get("mqtt_receivedQos"));
        };
    }

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

    private Map<String, Object> parsePayloadToMap(String payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, Map.class);
        } catch (Exception e) {
            logger.error("Error parsing payload", e);
            return new HashMap<>();  // Return empty Map on error
        }
    }


    private void setAttribute(Object obj, String attributeName, Object value) throws Exception {
        Class<?> objClass = obj.getClass();
        Field field = objClass.getDeclaredField(attributeName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
