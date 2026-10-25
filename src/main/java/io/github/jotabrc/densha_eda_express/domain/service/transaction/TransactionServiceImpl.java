package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.entity.TransactionRuleEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long.InventoryLongTransactionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRuleEntityService transactionRuleEntityService;
    private final InventoryLongTransactionResolver inventoryLongTransactionResolver;

    @Override
    public InventoryEntity execute(InventoryEntity inventory, Long quantity, TransactionType type) {
        TransactionRuleEntity transactionRule = transactionRuleEntityService.findByTriggerTypeOrElseThrow(type);
        inventoryLongTransactionResolver.execute(inventory, quantity, type);
        transactionRule.getTypes()
                .forEach(transactionType ->
                        inventoryLongTransactionResolver.execute(inventory, quantity, transactionType
                        ));
        return inventory;
    }
}
