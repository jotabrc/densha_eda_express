package io.github.jotabrc.densha_eda_express.repository;

import io.github.jotabrc.densha_eda_express.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findById(UUID id);
}
