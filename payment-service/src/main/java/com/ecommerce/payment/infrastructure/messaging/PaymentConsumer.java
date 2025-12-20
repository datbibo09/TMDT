package com.ecommerce.payment.infrastructure.messaging;

import com.ecommerce.payment.application.PaymentService;
import com.ecommerce.payment.infrastructure.messaging.dto.OrderEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PaymentConsumer {

    @Inject
    PaymentService paymentService;

    @Incoming("orders-in")
    public void consumeOrderCreated(OrderEvent event) {
        // Delegate logic sang Application Service
        paymentService.processPayment(event.orderId, event.totalAmount);
    }
}