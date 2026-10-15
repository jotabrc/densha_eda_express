package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_inventory")
public class Inventory extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @DecimalMin("0.00")
    @Column(name = "reserved_quantity", nullable = false)
    private BigDecimal reservedQuantity;

    @DecimalMin("0.00")
    @Column(name = "stock", nullable = false)
    private BigDecimal stock;

    public boolean hasStock(BigDecimal required) {
        return stock.add(reservedQuantity).add(required).compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean reserve(BigDecimal quantity) {
        if (hasStock(quantity)) {
            this.reservedQuantity = this.reservedQuantity.add(quantity);
            return true;
        }
        return false;
    }

    public void cancelReservation(BigDecimal quantity) {
        this.reservedQuantity = this.reservedQuantity.subtract(quantity);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        Inventory inventory = (Inventory) object;
        return productId.equals(inventory.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
