package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long;

import io.github.jotabrc.densha_eda_express.domain.entity.InventoryEntity;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryLongTransactionResolver {

    private final Map<TransactionType, InventoryLongTransactionRuleStrategy> rules = new HashMap<>();

    public InventoryLongTransactionResolver(List<InventoryLongTransactionRuleStrategy> rules) {
        rules.forEach(rule -> this.rules.put(rule.type(), rule));
    }

    public void execute(InventoryEntity inventory, Long quantity, TransactionType type) {
        rules.get(type).apply(inventory, quantity);
    }
}
