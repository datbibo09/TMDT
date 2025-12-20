package com.example.orders.application.service;

import com.example.orders.application.dto.InventoryResponse;
import com.example.orders.application.dto.OrderRequest;
import com.example.orders.application.mapper.OrderMapper;
import com.example.orders.domain.exception.BusinessException;
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
        // 1. Kiểm tra tồn kho
        InventoryResponse inventory;
        try {
            inventory = inventoryClient.getInventoryById(request.getProductId());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND, "Không thể kết nối đến dịch vụ kho");
        }

        if (inventory == null || inventory.quantity < request.getQuantity()) {
            throw new BusinessException(ErrorCode.INSUFFICIENT_STOCK);
        }

        // 2. Tạo đơn hàng (Trạng thái ban đầu là PENDING)
        Order order = orderMapper.toEntity(request);
        order.setStatus("PENDING");

        // Giả sử lấy giá từ inventory để tính tổng tiền
        if (inventory.price != null) {
            order.setTotalAmount(inventory.price * request.getQuantity());
        }

        orderRepository.persist(order);

        // 3. Thực hiện trừ kho (Quan trọng: Nếu lỗi ở đây, DB sẽ rollback xóa Order)
        try {
            inventoryClient.reduceStock(request.getProductId(), request.getQuantity());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "Lỗi khi cập nhật kho hàng");
        }

        // 4. Gửi event sang RabbitMQ cho Payment-Service xử lý
        eventProducer.sendOrderCreatedEvent(order.getId());

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.listAll();
    }

    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new BusinessException(ErrorCode.ORDER_NOT_FOUND);
        }
        return order;
    }
    @Transactional
    public boolean deleteOrder(Long id) {
        return orderRepository.deleteById(id);
    }
}