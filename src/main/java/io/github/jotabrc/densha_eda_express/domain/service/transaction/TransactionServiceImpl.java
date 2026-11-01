package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long.InventoryLongTransactionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService<InventoryEntity, Long, InventoryEntity> {

    private final InventoryLongTransactionResolver inventoryLongTransactionResolver;

    @Override
    public InventoryEntity execute(InventoryEntity entity, Long value, TransactionType type) {
        inventoryLongTransactionResolver.execute(entity, value, type);
        type.getTypes().forEach(t -> inventoryLongTransactionResolver.execute(entity, value, t));
        return entity;
    }
}
