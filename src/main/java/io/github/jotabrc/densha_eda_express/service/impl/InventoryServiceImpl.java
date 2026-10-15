package io.github.jotabrc.densha_eda_express.service.impl;

import io.github.jotabrc.densha_eda_express.domain.Inventory;
import io.github.jotabrc.densha_eda_express.repository.InventoryRepository;
import io.github.jotabrc.densha_eda_express.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    @Override
    public Inventory save(final Inventory entity) {
        return repository.save(entity);
    }

    @Override
    public Inventory update(final Inventory entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(final Inventory entity) {
        repository.delete(entity);
    }

    @Override
    public Inventory findById(final UUID id) {
        return repository.findById(UUID.fromString(id.toString()))
                .orElseThrow(() -> new IllegalStateException(Inventory.class.getSimpleName() + " not found: %s".formatted(id)));
    }

    @Override
    public Inventory findByProductId(final UUID productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new IllegalStateException(Inventory.class.getSimpleName() + " not found: %s".formatted(productId)));
    }

    @Override
    public List<Inventory> saveAll(Iterable<Inventory> entities) {
        return repository.saveAll(entities);
    }
}
