package io.github.jotabrc.densha_eda_express.domain.service.transaction.big_decimal;

import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReserveAddBigDecimalStrategy implements BigDecimalTransactionRule {

    @Override
    public BigDecimal apply(BigDecimal accumulator, BigDecimal operand) {
        return accumulator.add(operand);
    }

    @Override
    public TransactionType type() {
        return TransactionType.RESERVE_ADD;
    }
}
