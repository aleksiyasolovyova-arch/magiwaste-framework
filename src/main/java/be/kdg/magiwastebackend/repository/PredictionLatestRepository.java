package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.PredictionLatest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PredictionLatestRepository extends JpaRepository<PredictionLatest, Long> {

    @Query(value = """
        SELECT DISTINCT ON (bin_id) *
        FROM bin_prediction_ttf
        ORDER BY bin_id, predicted_at DESC
    """, nativeQuery = true)
    List<PredictionLatest> findAllLatestPerBin();

    @Query(value = """
        SELECT DISTINCT ON (bin_id) *
        FROM bin_prediction_ttf
        WHERE bin_id = ANY(:binIds)
        ORDER BY bin_id, predicted_at DESC
    """, nativeQuery = true)
    List<PredictionLatest> findLatestPerBinForIds(@org.springframework.data.repository.query.Param("binIds") Long[] binIds);

}
