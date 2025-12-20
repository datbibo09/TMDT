package com.example.orders.infrastructure.client;

import com.example.orders.application.dto.InventoryResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

// configKey này dùng để cấu hình URL trong application.properties
@RegisterRestClient(configKey = "inventory-api")
@Path("/inventory") // Đường dẫn gốc của inventory-service
public interface InventoryClient {

    // Gọi API: GET http://localhost:8083/inventory/{id}
    // Chú ý: Ở đây ta đang giả định productId chính là ID của inventory
    // Nếu logic của bạn khác (1 product có nhiều inventory), bạn cần API tìm theo productId
    @GET
    @Path("/{id}")
    InventoryResponse getInventoryById(@PathParam("id") Long id);
}