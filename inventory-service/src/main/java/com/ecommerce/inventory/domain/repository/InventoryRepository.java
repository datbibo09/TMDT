package com.ecommerce.inventory.domain.repository;

import com.ecommerce.inventory.domain.model.InventoryItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryRepository implements PanacheRepository<InventoryItem> {
}