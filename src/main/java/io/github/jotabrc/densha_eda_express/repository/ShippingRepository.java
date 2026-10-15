package io.github.jotabrc.densha_eda_express.repository;

import io.github.jotabrc.densha_eda_express.domain.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {

    Optional<Shipping> findById(UUID id);
}
