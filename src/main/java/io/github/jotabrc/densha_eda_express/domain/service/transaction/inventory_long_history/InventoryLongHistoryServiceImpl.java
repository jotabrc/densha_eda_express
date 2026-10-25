package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.entity.InventoryHistoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;

public class InventoryLongHistoryServiceImpl implements InventoryLongHistoryService {

    @Override
    public void apply(InventoryEntity inventory, TransactionType type) {
        inventory.addHistory(InventoryHistoryEntity.builder()
                .productId(inventory.getProductId())
                .type(type)
                .reserved(inventory.getReserved())
                .stock(inventory.getStock())
                .build());
    }
}
