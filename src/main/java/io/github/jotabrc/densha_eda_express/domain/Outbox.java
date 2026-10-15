package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @Min(0)
    @Builder.Default
    private int retries = 0;

    @Column(name = "error_message")
    private String errorMessage;

    @Transient
    private Object entity;
}
