package com.example.orders.application.mapper;

import com.example.orders.application.dto.OrderRequest;
import com.example.orders.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderMapper {
    public Order toEntity(OrderRequest request) {
        Order order = new Order();
        order.setOrderName(request.orderName());
        order.setStatus(request.status());
        order.setTotalAmount(request.totalAmount());
        order.setCreatedDate(request.createdDate());
        return order; // <-- Đã thêm dòng này
    }
}