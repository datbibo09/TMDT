package com.example.orders.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- QUAN TRỌNG: Đã thêm 2 trường này để sửa lỗi ---
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;
    // ---------------------------------------------------

    private String orderName;
    private String status;
    private Double totalAmount;
    public Double unitPrice;  // Giá tại thời điểm mua
    public Double totalPrice; // unitPrice * quantity
    public Long userId;
    // Ngày tạo (input từ request)
    private LocalDateTime createdDate;

    // Time tạo (audit hệ thống)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }

    // --- BẮT ĐẦU PHẦN GETTER / SETTER THỦ CÔNG ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // --- THÊM GETTER/SETTER CHO PRODUCT ID & QUANTITY ---
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    // ----------------------------------------------------

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}