package com.ecommerce.payment.domain.repository;

import com.ecommerce.payment.domain.model.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
    public Payment findByOrderId(Long orderId) {
        return find("orderId", orderId).firstResult();
    }
}