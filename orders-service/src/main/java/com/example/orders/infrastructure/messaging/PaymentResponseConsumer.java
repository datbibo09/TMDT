package com.example.orders.infrastructure.messaging;

import com.example.orders.domain.model.Order;
import com.example.orders.domain.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PaymentResponseConsumer {

    @Inject
    OrderRepository orderRepository;

    @Incoming("payment-in") // Kênh này sẽ cấu hình trong application.properties
    @Transactional
    public void handlePaymentResult(com.example.orders.application.dto.PaymentEvent event) {
        Order order = orderRepository.findById(event.orderId);
        if (order != null) {
            if ("COMPLETED".equals(event.status)) {
                order.setStatus("PAID");
                System.out.println("Order " + order.getId() + " updated to PAID");
            } else {
                order.setStatus("PAYMENT_FAILED");
                System.out.println("Order " + order.getId() + " failed payment");
            }
        }
    }
}