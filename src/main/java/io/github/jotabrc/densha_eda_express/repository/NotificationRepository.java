package io.github.jotabrc.densha_eda_express.repository;

import io.github.jotabrc.densha_eda_express.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    Optional<Notification> findById(UUID id);
}
