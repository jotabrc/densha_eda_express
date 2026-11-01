package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import java.util.UUID;

public interface TransactionRule<T, U> {

    void apply(T t, U u, UUID orderId);

    TransactionType type();
}
