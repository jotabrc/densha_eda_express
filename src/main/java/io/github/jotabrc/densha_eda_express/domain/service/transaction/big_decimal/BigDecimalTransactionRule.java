package io.github.jotabrc.densha_eda_express.domain.service.transaction.big_decimal;

import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionRule;

import java.math.BigDecimal;

public interface BigDecimalTransactionRule extends TransactionRule<BigDecimal, BigDecimal, BigDecimal> {

    BigDecimal apply(BigDecimal accumulator, BigDecimal operand);

    TransactionType type();
}
