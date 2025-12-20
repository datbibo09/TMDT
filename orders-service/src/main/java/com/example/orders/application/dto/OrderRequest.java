package com.example.orders.application.dto;

import java.time.LocalDateTime;

// Request
public record OrderRequest(
        String orderName,
        String status,
        Double totalAmount,
        LocalDateTime createdDate
) {
    public Integer getQuantity() {
        return 0;
    }

    public Long getProductId() {
        return 0L;
    }
}