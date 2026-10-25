package io.github.jotabrc.densha_eda_express.domain.entity;


import io.github.jotabrc.densha_eda_express.domain.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InventoryEntity extends BaseObject {

    private Long stock;
    private Long reserved;
    private UUID productId;
    private List<InventoryHistoryEntity> history;

    public boolean isAvailable() {
        return stock - reserved >= 0;
    }

    public boolean reserve(Long quantity) {
        boolean isAvailable = isAvailable();
        if (isAvailable) {
            this.setReserved(this.getReserved() + quantity);
        }
        return isAvailable;
    }

    public boolean stock(Long quantity) {
        boolean isAvailable = isAvailable();
        if (isAvailable) {
            this.setStock(this.getStock() + quantity);
        }
        return isAvailable;
    }

    public void addHistory(InventoryHistoryEntity history) {
        this.getHistory().add(history);
    }
}
