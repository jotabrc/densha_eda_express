package io.github.jotabrc.densha_eda_express.domain.service.repositories;

import io.github.jotabrc.densha_eda_express.domain.entity_database.InventoryHistoryJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryHistoryRepository extends CrudRepository<InventoryHistoryJpa, UUID> {

    Optional<InventoryHistoryJpa> findByOrderIdAndProductId(UUID orderId, UUID productId);
}
