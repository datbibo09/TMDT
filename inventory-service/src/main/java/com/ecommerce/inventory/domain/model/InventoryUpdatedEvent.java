package com.ecommerce.inventory.domain.model;

import java.time.LocalDateTime;

public record InventoryUpdatedEvent(
        Long inventoryId,
        String inventoryCode,
        Long productId,
        int oldQuantity,
        int newQuantity,
        String location,
        LocalDateTime timestamp
) {
    public InventoryUpdatedEvent(Long inventoryId, String inventoryCode, Long productId, int oldQuantity, int newQuantity, String location) {
        this(inventoryId, inventoryCode, productId, oldQuantity, newQuantity, location, LocalDateTime.now());
    }
}