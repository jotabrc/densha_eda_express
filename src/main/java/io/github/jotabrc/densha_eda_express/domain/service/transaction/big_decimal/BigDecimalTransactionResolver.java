package io.github.jotabrc.densha_eda_express.domain.service.transaction.big_decimal;

import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BigDecimalTransactionResolver {

    private final Map<TransactionType, BigDecimalTransactionRule> rules = new HashMap<>();

    public BigDecimalTransactionResolver(List<BigDecimalTransactionRule> rules) {
        rules.forEach(rule -> this.rules.put(rule.type(), rule));
    }

    public BigDecimal operate(BigDecimal accumulator, BigDecimal operand, TransactionType type) {
        return rules.get(type).apply(accumulator, operand);
    }
}
