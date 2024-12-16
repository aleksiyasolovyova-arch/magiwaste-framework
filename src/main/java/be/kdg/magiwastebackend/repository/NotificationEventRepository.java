package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long> {

}
