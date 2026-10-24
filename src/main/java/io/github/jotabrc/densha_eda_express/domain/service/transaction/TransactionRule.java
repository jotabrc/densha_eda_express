package io.github.jotabrc.densha_eda_express.domain.service.transaction;

public interface TransactionRule<T, U, R> {

    R apply(T t, U u);

    TransactionType type();
}
