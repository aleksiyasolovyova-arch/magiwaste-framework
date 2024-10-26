package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.RawDataLog;
import be.kdg.magiwastebackend.repository.RawDataLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawDataLogServiceImplementation implements RawDataLogService{

    private final RawDataLogRepository rawDataLogRepository;

    public RawDataLogServiceImplementation(RawDataLogRepository rawDataLogRepository) {
        this.rawDataLogRepository = rawDataLogRepository;
    }

    @Override
    public List<RawDataLog> findAll() {
        return rawDataLogRepository.findAll();
    }

    @Override
    public RawDataLog save(RawDataLog rawDataLog) {
        return rawDataLogRepository.save(rawDataLog);
    }
}
