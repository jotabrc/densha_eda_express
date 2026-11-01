package io.github.jotabrc.densha_eda_express.domain.service.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public enum TransactionType {
    MAKE_RESERVATION(Collections.emptyList()),
    REMOVE_RESERVATION(Collections.emptyList()),
    ADD_STOCK(Collections.emptyList()),
    STOCK_WITHDRAWAL(List.of(REMOVE_RESERVATION));

    @Getter
    private final List<TransactionType> types;
}
