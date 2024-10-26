package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.WasteBin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteBinRepository extends JpaRepository<WasteBin, Long> {

}
