package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private UUID details;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
