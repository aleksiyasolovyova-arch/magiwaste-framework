package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.RawDataLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataLogRepository extends JpaRepository<RawDataLog, Long> {

}
