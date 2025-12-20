package com.example.orders.application.mapper;

import com.example.orders.application.dto.OrderRequest;
import com.example.orders.domain.model.Order; // <-- Nhớ import đúng Entity của bạn
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        if (request == null) {
            return null;
        }

        Order order = new Order();

        // Map dữ liệu thủ công từ Request sang Entity
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setOrderName(request.getOrderName());
        order.setTotalAmount(request.getTotalAmount());

        // Các trường mặc định (nếu request không gửi lên)
        if (order.getCreatedDate() == null) {
            order.setCreatedDate(LocalDateTime.now());
        }

        // Lưu ý: Status thường được set ở Service (như code bạn đang có: "PENDING")
        // nên ở đây có thể map hoặc bỏ qua tùy bạn.
        order.setStatus(request.getStatus());

        return order;
    }
}