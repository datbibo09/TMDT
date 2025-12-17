package com.example.orders.application.dto;
import java.time.LocalDateTime;
public record OrderResponse(
        Long id,
        String orderName,
        String status,
        Double totalAmount,
        LocalDateTime createdDate,
        LocalDateTime createdAt // Time tạo
) {}