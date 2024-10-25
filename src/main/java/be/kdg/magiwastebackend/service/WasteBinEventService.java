package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.WasteBinEvent;

import java.util.List;

public interface WasteBinEventService {
    List<WasteBinEvent> findAll();

    WasteBinEvent save(WasteBinEvent wasteBinEvent);
}
