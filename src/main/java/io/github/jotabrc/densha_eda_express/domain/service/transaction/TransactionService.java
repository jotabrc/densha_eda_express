package io.github.jotabrc.densha_eda_express.domain.service.transaction;

public interface TransactionService<T, U, R> {

    R execute(T entity, U value, TransactionType type);
}
