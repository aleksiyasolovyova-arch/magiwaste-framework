package be.kdg.magiwastebackend.service;

import be.kdg.magiwastebackend.domain.PredictionLatest;
import be.kdg.magiwastebackend.repository.PredictionLatestRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.function.Function;

@Service
public class PredictionLatestServiceImplementation implements PredictionLatestService{
    private final PredictionLatestRepository repo;

    public PredictionLatestServiceImplementation(PredictionLatestRepository repo) {
        this.repo = repo;
    }


    @Override
    public Map<Long, TtfPredictionVM> latestByBin() {
        return repo.findAllLatestPerBin()
                .stream()
                .map(this::toVm)
                .collect(Collectors.toMap(TtfPredictionVM::binId, Function.identity()));
    }

    @Override
    public Optional<TtfPredictionVM> latestForBin(Long binId) {
        return Optional.empty();
    }

    private TtfPredictionVM toVm(PredictionLatest p) {
        return new TtfPredictionVM(
                p.getBinId(),
                p.getPredictedHours(),
                p.getPredictedAt()

        );
    }

}
