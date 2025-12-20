package com.ecommerce.inventory.application;

import com.ecommerce.inventory.domain.event.InventoryEvent;
import com.ecommerce.inventory.domain.model.InventoryItem;
import com.ecommerce.inventory.infrastructure.messaging.InventoryEventProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InventoryService {

    @Inject
    InventoryEventProducer eventProducer;

    @Transactional
    public InventoryItem create(InventoryItem item) {
        // Kiểm tra mã trùng
        if (InventoryItem.find("inventoryCode", item.getInventoryCode()).firstResult() != null) {
            throw new IllegalArgumentException("Mã tồn kho đã tồn tại");
        }
        item.persist();
        eventProducer.publish(item, InventoryEvent.EventType.CREATED);
        return item;
    }

    @Transactional
    public InventoryItem increaseQuantity(Long id, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Số lượng tăng phải > 0");

        InventoryItem item = InventoryItem.findById(id);
        if (item == null) throw new IllegalArgumentException("Không tìm thấy tồn kho với id: " + id);

        item.setQuantity(item.getQuantity() + amount);
        eventProducer.publish(item, InventoryEvent.EventType.QUANTITY_INCREASED);
        return item;
    }

    @Transactional
    public InventoryItem decreaseQuantity(Long id, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Số lượng giảm phải > 0");

        InventoryItem item = InventoryItem.findById(id);
        if (item == null) throw new IllegalArgumentException("Không tìm thấy tồn kho với id: " + id);

        if (item.getQuantity() < amount) throw new IllegalArgumentException("Tồn kho không đủ");

        item.setQuantity(item.getQuantity() - amount);
        eventProducer.publish(item, InventoryEvent.EventType.QUANTITY_DECREASED);
        return item;
    }

    public InventoryItem findById(Long id) {
        return InventoryItem.findById(id);
    }

    public List<InventoryItem> listAll() {
        return InventoryItem.listAll();
    }
}