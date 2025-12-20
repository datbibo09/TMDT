package com.ecommerce.payment.infrastructure.messaging.dto;

public class OrderEvent {
    public Long orderId;
    public Double totalAmount;
    // Thêm các field khác nếu Order Service gửi sang
}