package be.kdg.magiwastebackend.repository;

import be.kdg.magiwastebackend.domain.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long> {
}
