package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history.InventoryLongHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReserveAddInventoryLongStrategy implements InventoryLongTransactionRuleStrategy {

    private final InventoryLongHistoryService inventoryLongHistoryService;

    @Override
    public void apply(InventoryEntity inventory, Long quantity) {
        inventory.reserve(quantity);
        inventoryLongHistoryService.apply(inventory, this.type());
    }

    @Override
    public TransactionType type() {
        return TransactionType.RESERVE_ADD;
    }
}
