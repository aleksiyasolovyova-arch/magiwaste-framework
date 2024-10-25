package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.RawDataLog;

import java.util.List;

public interface RawDataLogService {
    List<RawDataLog> findAll();

    RawDataLog save(RawDataLog rawDataLog);
}
