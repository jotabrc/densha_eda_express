package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_shipping")
public class Notification extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(name = "order_id", nullable = false)
    private String orderId;
}
