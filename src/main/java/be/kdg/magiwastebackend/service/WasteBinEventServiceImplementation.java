package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.repository.WasteBinEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteBinEventServiceImplementation implements WasteBinEventService {
    private final WasteBinEventRepository wasteBinEventRepository;

    @Autowired
    public WasteBinEventServiceImplementation(WasteBinEventRepository wasteBinEventRepository) {
        this.wasteBinEventRepository = wasteBinEventRepository;
    }

    @Override
    public List<WasteBinEvent> findAll() {
        return wasteBinEventRepository.findAll();
    }

    @Override
    public WasteBinEvent save(WasteBinEvent wasteBinEvent) {
        return wasteBinEventRepository.save(wasteBinEvent);
    }

    @Override
    public List<WasteBinEvent> findAllByBin(WasteBin bin) {
        return wasteBinEventRepository.findAllByBinOrderByEventDate(bin);
    }

}
