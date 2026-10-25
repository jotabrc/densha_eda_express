package io.github.jotabrc.densha_eda_express.domain.service.transaction.inventory_long_history;

import io.github.jotabrc.densha_eda_express.domain.service.transaction.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class TransactionHolder {

    private final Long quantity;
    private final TransactionType type;
}
