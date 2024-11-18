package be.kdg.magiwastebackend.payloadhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;

@Component
class PayloadCleaner {
    Logger logger = LoggerFactory.getLogger(PayloadCleaner.class);

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
