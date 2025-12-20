package com.example.orders.interfaces.rest;

import com.example.orders.application.dto.OrderRequest;
import com.example.orders.application.dto.OrderResponse;
import com.example.orders.application.service.OrderService;
// Lưu ý: Kiểm tra lại package chứa class Order (Entity) của bạn và import vào đây


import com.example.orders.domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    public Response createOrder(OrderRequest request) {
        // 1. Nhận Entity từ Service
        Order savedOrder = orderService.createOrder(request);

        // 2. Chuyển đổi sang DTO
        OrderResponse response = mapToResponse(savedOrder);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public List<OrderResponse> getAllOrders() {
        // Lấy List<Order> từ service và chuyển từng cái thành OrderResponse
        return orderService.getAllOrders().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public OrderResponse getOrderById(@PathParam("id") Long id) {
        Order order = orderService.getOrderById(id);
        return mapToResponse(order);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderService.deleteOrder(id);
        return Response.noContent().build();
    }

    // --- Hàm phụ trợ để Map từ Entity sang DTO ---
    // Trong file OrderResource.java

    private OrderResponse mapToResponse(Order order) {
        if (order == null) return null;

        return new OrderResponse(
                order.getId(),              // 1. id
                order.getProductId(),       // 2. productId
                order.getQuantity(),        // 3. quantity
                order.getOrderName(),       // 4. orderName
                order.getStatus(),          // 5. status
                order.getTotalAmount(),     // 6. totalAmount
                order.getCreatedDate(),     // 7. createdDate
                order.getCreatedAt()        // 8. createdAt
        );
    }
}