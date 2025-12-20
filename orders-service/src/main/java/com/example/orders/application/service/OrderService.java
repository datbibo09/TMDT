package com.example.orders.application.service;

import com.example.orders.application.dto.InventoryResponse;
import com.example.orders.application.dto.OrderRequest;
import com.example.orders.application.mapper.OrderMapper;
import com.example.orders.domain.exception.BusinessException;
import com.example.orders.domain.exception.OrderNotFoundException;
import com.example.orders.domain.model.ErrorCode;
import com.example.orders.domain.model.Order;
import com.example.orders.domain.repository.OrderRepository;
import com.example.orders.infrastructure.client.InventoryClient;
import com.example.orders.infrastructure.messaging.OrderEventProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderMapper orderMapper;

    @Inject
    OrderEventProducer eventProducer;

    @Inject
    @RestClient
    InventoryClient inventoryClient;

    @Transactional
    public Order createOrder(OrderRequest request) {
        // --- 1. KIỂM TRA TỒN KHO ---
        InventoryResponse inventory;
        try {
            inventory = inventoryClient.getInventoryById(request.getProductId());
        } catch (Exception e) {
            // SỬA TẠI ĐÂY: Xóa cặp ngoặc {} và dấu ; thừa
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "Không thể kết nối đến kho: " + e.getMessage());
        }

        if (inventory == null) {
            // SỬA TẠI ĐÂY: Xóa {}
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        if (inventory.quantity < request.getQuantity()) {
            // SỬA TẠI ĐÂY: Xóa {}
            throw new BusinessException(ErrorCode.INSUFFICIENT_STOCK,
                    "Kho không đủ hàng! Hiện chỉ còn: " + inventory.quantity);
        }

        // --- 2. TẠO ORDER ---
        Order order = orderMapper.toEntity(request);
        order.setStatus("PENDING");

        orderRepository.persist(order);

        // --- 3. GỬI EVENT ---
        // eventProducer.sendOrderCreatedEvent(order);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.listAll();
    }

    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.delete(order);
    }
}