package com.example.orders.application.dto;

public class InventoryResponse {
    public Long id;
    public String inventoryCode;
    public Long productId;
    public Integer quantity;
    public String location;
    public Double price; // Thêm dòng này để OrderService có thể truy cập
}