package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionType {
    RESERVE_ADD,
    RESERVE_REMOVE,
    STOCK_ADD,
    STOCK_REMOVE
}
