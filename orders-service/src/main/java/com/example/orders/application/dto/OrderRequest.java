package com.example.orders.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long productId;      // Đã thêm trường này
    private Integer quantity;    // Thường đặt hàng sẽ cần số lượng
    private String orderName;
    private String status;
    private Double totalAmount;
    private LocalDateTime createdDate;
}