package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long> {
}
