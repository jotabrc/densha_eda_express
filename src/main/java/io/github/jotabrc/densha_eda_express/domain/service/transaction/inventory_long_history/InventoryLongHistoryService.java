package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.entity.InventoryHistoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;

import java.util.UUID;

public interface InventoryLongHistoryService {

    InventoryHistoryEntity findByOrderIdAndProductId(UUID orderId, UUID productId);

    void apply(InventoryEntity inventory, TransactionType type, UUID orderId);
}
