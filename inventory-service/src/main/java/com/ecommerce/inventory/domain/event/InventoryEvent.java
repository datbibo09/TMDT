package com.ecommerce.inventory.domain.event;

import com.ecommerce.inventory.domain.model.InventoryItem;
import java.time.LocalDateTime;

public record InventoryEvent(
        EventType type,
        InventoryItem item,
        LocalDateTime timestamp
) {
    public InventoryEvent(EventType type, InventoryItem item) {
        this(type, item, LocalDateTime.now());
    }

    public enum EventType {
        CREATED, UPDATED, QUANTITY_INCREASED, QUANTITY_DECREASED, DELETED
    }
}