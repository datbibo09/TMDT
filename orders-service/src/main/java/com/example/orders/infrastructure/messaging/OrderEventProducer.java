package com.example.orders.infrastructure.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class OrderEventProducer {

    @Channel("orders-out")
    Emitter<String> orderEmitter;

    public void sendOrderCreatedEvent(Long orderId) {
        // Gửi message đơn giản chứa ID hoặc JSON object
        orderEmitter.send("Order Created with ID: " + orderId);
    }
}