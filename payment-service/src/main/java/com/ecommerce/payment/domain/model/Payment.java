package com.ecommerce.payment.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment extends PanacheEntity {

    public Long orderId;
    public Double amount;

    @Enumerated(EnumType.STRING)
    public PaymentStatus status;

    public LocalDateTime createdAt;

    public static Payment create(Long orderId, Double amount) {
        Payment p = new Payment();
        p.orderId = orderId;
        p.amount = amount;
        p.status = PaymentStatus.PENDING;
        p.createdAt = LocalDateTime.now();
        return p;
    }
}