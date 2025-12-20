package com.example.orders.application.service;

import com.example.orders.application.dto.InventoryResponse;
import com.example.orders.application.dto.OrderRequest;
import com.example.orders.application.mapper.OrderMapper;
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
        // --- 1. KIỂM TRA TỒN KHO TRƯỚC KHI TẠO ---
        try {
            // Gọi sang Inventory Service
            InventoryResponse inventory = inventoryClient.getInventoryById(request.getProductId());

            if (inventory == null) {
                throw new RuntimeException("Sản phẩm không tồn tại trong kho!");
            }

            // Lưu ý: Đảm bảo class InventoryResponse có field 'quantity' là public hoặc có getter
            if (inventory.quantity < request.getQuantity()) {
                throw new RuntimeException("Kho không đủ hàng! Hiện chỉ còn: " + inventory.quantity);
            }

        } catch (Exception e) {
            // Ném lỗi để chặn việc tạo đơn hàng nếu check kho thất bại
            throw new RuntimeException("Lỗi xác thực tồn kho: " + e.getMessage());
        }

        // --- 2. TẠO ORDER NẾU ĐỦ HÀNG ---
        Order order = orderMapper.toEntity(request);
        order.setStatus("PENDING");

        orderRepository.persist(order);

        // --- 3. GỬI EVENT (Optional) ---
        // eventProducer.sendOrderCreatedEvent(order);

        return order;
    }

    // --- CÁC HÀM CRUD BỔ SUNG (BẮT BUỘC ĐỂ KHỚP VỚI RESOURCE) ---

    public List<Order> getAllOrders() {
        return orderRepository.listAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void deleteOrder(Long id) {
        boolean deleted = orderRepository.deleteById(id);
        if (!deleted) {
            throw new RuntimeException("Không tìm thấy đơn hàng để xóa với ID: " + id);
        }
    }
}