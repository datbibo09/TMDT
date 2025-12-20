package com.ecommerce.inventory.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "inventory_items")
public class InventoryItem extends PanacheEntity {

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Mã tồn kho không được trống")
    private String inventoryCode;

    @Column(nullable = false)
    @NotNull(message = "Product ID không được trống")
    private Long productId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Kho không được trống")
    private LocationCode location;

    @Column(nullable = false)
    @Min(value = 0, message = "Số lượng phải >= 0")
    private int quantity = 0;

    public InventoryItem() {}

    // Getters & Setters
    public String getInventoryCode() { return inventoryCode; }
    public void setInventoryCode(String inventoryCode) { this.inventoryCode = inventoryCode; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public LocationCode getLocation() { return location; }
    public void setLocation(LocationCode location) { this.location = location; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}