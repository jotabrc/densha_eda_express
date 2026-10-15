package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_shipping")
public class Shipping extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;
}
