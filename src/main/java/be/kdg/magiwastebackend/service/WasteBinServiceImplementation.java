package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.repository.WasteBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteBinServiceImplementation implements WasteBinService {
    private final WasteBinRepository wasteBinRepository;

    @Autowired
    public WasteBinServiceImplementation(WasteBinRepository wasteBinRepository) {
        this.wasteBinRepository = wasteBinRepository;
    }

    @Override
    public List<WasteBin> findAll() {
        return wasteBinRepository.findAll();
    }

    @Override
    public WasteBin save(WasteBin wasteBin) {
        return wasteBinRepository.save(wasteBin);
    }

    @Override
    public WasteBin findWasteBinById(Long binId) {
        return wasteBinRepository.findById(binId).stream().findFirst().orElse(null);
    }


}
