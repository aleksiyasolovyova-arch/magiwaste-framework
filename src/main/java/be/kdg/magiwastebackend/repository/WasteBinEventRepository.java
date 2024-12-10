package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteBinEventRepository extends JpaRepository<WasteBinEvent, Long> {

    List<WasteBinEvent> findAllByBinOrderByEventDate(WasteBin bin);

}
