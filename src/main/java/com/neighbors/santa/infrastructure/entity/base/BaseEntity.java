package com.neighbors.santa.infrastructure.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Column(name = "status")
    public String status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    public LocalDateTime updated_at;
}
