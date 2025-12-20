package com.ecommerce.inventory.infrastructure.messaging;

import com.ecommerce.inventory.domain.event.InventoryEvent;
import com.ecommerce.inventory.domain.model.InventoryItem;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class InventoryEventProducer {

    @Channel("inventory-event")
    Emitter<InventoryEvent> emitter;

    public void publish(InventoryItem item, InventoryEvent.EventType type) {
        InventoryEvent event = new InventoryEvent(type, item);

        emitter.send(event)
                .whenComplete((success, failure) -> {
                    if (failure != null) {
                        System.out.println("Failed to send inventory event: " + failure.getMessage());
                        failure.printStackTrace(); // in stack trace để debug dễ hơn
                    } else {
                        System.out.println("Sent inventory event: " + type + " - " + item.getInventoryCode());
                    }
                });
    }
}