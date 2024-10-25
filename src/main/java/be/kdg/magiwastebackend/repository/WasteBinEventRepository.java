package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteBinEventRepository extends JpaRepository<WasteBinEvent, Long> {

}
