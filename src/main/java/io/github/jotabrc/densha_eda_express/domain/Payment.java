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
@Table(name = "tb_payment")
public class Payment extends BaseEntity {

    @DecimalMin("0.0")
    @Column(nullable = false)
    private BigDecimal value;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;
}
