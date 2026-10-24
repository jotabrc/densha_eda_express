package io.github.jotabrc.densha_eda_express.domain.service.transaction.big_decimal;

import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StockRemoveBigDecimalStrategy implements BigDecimalTransactionRule {

    @Override
    public BigDecimal apply(BigDecimal accumulator, BigDecimal operand) {
        return accumulator.subtract(operand);
    }

    @Override
    public TransactionType type() {
        return TransactionType.STOCK_REMOVE;
    }
}
