package io.github.jotabrc.densha_eda_express.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_date", nullable = false, updatable = false)
    private OffsetDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    private OffsetDateTime updatedDate;

    @Version
    private int version;

    @PrePersist
    protected void prePersist() {
        if (isNull(this.getCreatedDate())) {
            this.setCreatedDate(OffsetDateTime.now());
        }

        if (isNull(this.getUpdatedDate())) {
            this.setUpdatedDate(OffsetDateTime.now());
        }

        if (isNull(this.getId())) {
            this.setId(UUID.randomUUID());
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.setUpdatedDate(OffsetDateTime.now());
    }
}
