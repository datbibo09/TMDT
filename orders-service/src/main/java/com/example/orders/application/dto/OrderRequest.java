package com.example.orders.application.dto;

import java.time.LocalDateTime;

// Request
public record OrderRequest(
        String orderName,
        String status,
        Double totalAmount,
        LocalDateTime createdDate
) {}