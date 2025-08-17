package be.kdg.magiwastebackend.service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;


public interface PredictionLatestService {

    record TtfPredictionVM(long binId, double predictedHours, Instant predictedAt) {}
    Map<Long, TtfPredictionVM> latestByBin();
    Optional<TtfPredictionVM> latestForBin(Long binId);
}

