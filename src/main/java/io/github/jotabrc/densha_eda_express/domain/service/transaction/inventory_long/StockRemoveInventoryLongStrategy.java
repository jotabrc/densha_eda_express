package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history.InventoryLongHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StockRemoveInventoryLongStrategy implements InventoryLongTransactionRuleStrategy {

    private final InventoryLongHistoryService inventoryLongHistoryService;

    @Override
    public void apply(InventoryEntity inventory, Long quantity, UUID orderId) {
        inventory.stock(quantity);
        inventoryLongHistoryService.apply(inventory, this.type(), orderId);
    }

    @Override
    public TransactionType type() {
        return TransactionType.STOCK_WITHDRAWAL;
    }
}
