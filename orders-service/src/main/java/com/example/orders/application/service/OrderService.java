package com.example.orders.application.service;

import com.example.orders.application.dto.OrderRequest;
import com.example.orders.application.dto.OrderResponse;
import com.example.orders.domain.model.Order;
import com.example.orders.domain.repository.OrderRepository;
import com.example.orders.infrastructure.messaging.OrderEventProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderEventProducer eventProducer;

    @Inject
    EntityManager em;

    // Create Order
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderName(request.orderName());
        order.setStatus(request.status());
        order.setTotalAmount(request.totalAmount());
        order.setCreatedDate(request.createdDate());

        orderRepository.persist(order);
        orderRepository.flush();

        // ✅ đảm bảo có ID ngay (nếu id được DB generate)
        em.flush();

        // ✅ tránh 500 nếu RabbitMQ/config đang lỗi: vẫn tạo order được
        try {
            eventProducer.sendOrderCreatedEvent(order.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapToResponse(order);
    }

    // Get All
    public List<OrderResponse> getAllOrders() {
        return orderRepository.listAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get by ID
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new NotFoundException("Order not found with id " + id);
        }
        return mapToResponse(order);
    }

    // Delete by ID
    @Transactional
    public void deleteOrder(Long id) {
        boolean deleted = orderRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Cannot delete. Order not found with id " + id);
        }
    }

    // Mapper thủ công (có thể dùng MapStruct nếu muốn)
    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderName(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getCreatedDate(),
                order.getCreatedAt()
        );
    }
}
