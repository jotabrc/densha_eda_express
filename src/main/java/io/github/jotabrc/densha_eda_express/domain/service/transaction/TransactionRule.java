package io.github.jotabrc.densha_eda_express.domain.service.transaction;

public interface TransactionRule<T, U> {

    void apply(T t, U u);

    TransactionType type();
}
