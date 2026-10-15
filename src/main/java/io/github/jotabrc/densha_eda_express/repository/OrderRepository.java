package io.github.jotabrc.densha_eda_express.repository;

import io.github.jotabrc.densha_eda_express.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findById(UUID id);
}
