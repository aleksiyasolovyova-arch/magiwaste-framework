package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.http.HttpMessage;

import java.util.Map;

public interface PayloadService {
    public void getAndSendData(HttpMessage httpMessage);

    //testing method overloading real quick
    public void getAndSendData(Long test);


    void doYourThing(Map<String, Object> body);
}
