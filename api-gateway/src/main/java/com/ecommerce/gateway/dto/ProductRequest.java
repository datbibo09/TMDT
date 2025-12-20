package com.ecommerce.gateway.dto;

public record ProductRequest(
        String name,
        double price,
        int quantity
) {}
