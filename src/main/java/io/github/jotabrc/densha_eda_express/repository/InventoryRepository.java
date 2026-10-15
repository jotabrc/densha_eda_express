package io.github.jotabrc.densha_eda_express.repository;

import io.github.jotabrc.densha_eda_express.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    Optional<Inventory> findByProductId(UUID productId);
}
