package com.example.orders.domain.repository;

import com.example.orders.domain.model.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
    // Có thể thêm custom query method tại đây nếu cần
}