package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.WeatherEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherEventRepository  extends JpaRepository<WeatherEvent, Long> {

}
