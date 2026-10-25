package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;

public interface TransactionService {

    InventoryEntity execute(InventoryEntity inventory, Long quantity, TransactionType type);
}
