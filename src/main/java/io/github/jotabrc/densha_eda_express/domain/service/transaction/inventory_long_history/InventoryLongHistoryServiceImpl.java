package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.entity.InventoryHistoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.repositories.InventoryHistoryRepository;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class InventoryLongHistoryServiceImpl implements InventoryLongHistoryService {

    private final InventoryHistoryRepository repository;

    @Override
    public InventoryHistoryEntity findByOrderIdAndProductId(UUID orderId, UUID productId) {
        return repository.findByOrderIdAndProductId(orderId, productId)
                .map(InventoryHistoryEntity::fromJpa)
                .orElse(null);
    }

    @Override
    public void apply(InventoryEntity inventory, TransactionType type, UUID orderId) {
        inventory.addHistory(InventoryHistoryEntity.builder()
                .inventory(InventoryEntity.builder().id(inventory.getId()).build())
                .type(type)
                .reserved(inventory.getReserved())
                .stock(inventory.getStock())
                .orderId(orderId)
                .build());
    }
}
