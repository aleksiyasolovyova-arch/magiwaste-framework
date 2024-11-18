package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.repository.WasteBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public WasteBin findBinByDeviceId(String deviceId) {
        Optional<WasteBin> bin = wasteBinRepository.findByDeviceId(deviceId);
        //if a bin matching the deviceId is found, return it. If not, make a new bin with new id
        if (bin.isPresent()) {
            return bin.get();
        } else {
            WasteBin newBin = new WasteBin();
            newBin.setDeviceId(deviceId);
            wasteBinRepository.save(newBin);
            return newBin;
        }

    }


}
