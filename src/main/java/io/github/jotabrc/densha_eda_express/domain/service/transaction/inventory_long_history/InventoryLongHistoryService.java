package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;

public interface InventoryLongHistoryService {

    void apply(InventoryEntity inventory, TransactionType type);
}
