package io.github.jotabrc.densha_eda_express.transaction_outbox.outbox;

import io.github.jotabrc.densha_eda_express.domain.BaseEntity;
import io.github.jotabrc.densha_eda_express.transaction_outbox.outbox.enums.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_outbox")
public class Outbox extends BaseEntity {

    @Column(nullable = false)
    private String topic;

    private String key;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OutboxStatus status;

    @Column(nullable = false)
    private byte[] event;

    @Column(name = "event_fqcn", nullable = false)
    private String eventFqcn;

    @Column(nullable = false)
    private int retries = 0;

    @Column(name = "error_message")
    private String errorMessage;
}
