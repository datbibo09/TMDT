package com.example.orders.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long productId;      // Thêm mới
    private Integer quantity;    // Thêm mới
    private String orderName;
    private String status;
    private Double totalAmount;
    private LocalDateTime createdDate;
    private LocalDateTime createdAt;
}