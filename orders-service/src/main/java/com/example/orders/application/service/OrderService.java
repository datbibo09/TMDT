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

    // 1. Tạo đơn hàng
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderName(request.orderName());
        order.setStatus(request.status());
        order.setTotalAmount(request.totalAmount());
        order.setCreatedDate(request.createdDate());

        orderRepository.persist(order);

        // Đẩy event ra RabbitMQ (nếu lỗi thì log ra console chứ không chặn tạo đơn)
        try {
            // Lưu ý: Cần flush để có ID trước khi gửi event
            orderRepository.flush();
            eventProducer.sendOrderCreatedEvent(order.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapToResponse(order);
    }

    // 2. Lấy tất cả
    public List<OrderResponse> getAllOrders() {
        return orderRepository.listAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 3. Lấy theo ID (Hàm này đang thiếu nên gây lỗi)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new NotFoundException("Order not found with id " + id);
        }
        return mapToResponse(order);
    }

    // 4. Xóa đơn hàng (Hàm này đang thiếu nên gây lỗi)
    @Transactional
    public void deleteOrder(Long id) {
        boolean deleted = orderRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Cannot delete. Order not found with id " + id);
        }
    }

    // Hàm chuyển đổi entity sang response
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