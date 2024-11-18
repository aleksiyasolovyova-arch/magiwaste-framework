package be.kdg.magiwastebackend.payloadhandling;

import org.springframework.http.HttpMessage;

public interface PayloadService {
    public void getAndSendData(HttpMessage httpMessage);

    //testing method overloading real quick
    public void getAndSendData(Long test);



}
