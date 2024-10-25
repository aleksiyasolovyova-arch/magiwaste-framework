package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;

import java.util.List;

public interface WasteBinService {

    List<WasteBin> findAll();
    WasteBin save(WasteBin wasteBin);
}
