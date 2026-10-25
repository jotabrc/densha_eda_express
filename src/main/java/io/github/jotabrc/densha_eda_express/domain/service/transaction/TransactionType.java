package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;

@RequiredArgsConstructor
public enum TransactionType {
    RESERVE_ADD(InventoryEntity::reserve),
    RESERVE_REMOVE(InventoryEntity::reserve),
    STOCK_ADD(InventoryEntity::stock),
    STOCK_REMOVE(InventoryEntity::stock);

    private final BiConsumer<InventoryEntity, Long> operand;

    public void execute(InventoryEntity inventory, Long quantity) {
        this.operand.accept(inventory, quantity);
    }
}
