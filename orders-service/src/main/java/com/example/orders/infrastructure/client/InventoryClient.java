package com.example.orders.infrastructure.client;

import com.example.orders.application.dto.InventoryResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "inventory-api")
@Path("/inventory")
public interface InventoryClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    InventoryResponse getInventoryById(@PathParam("id") Long id);

    // Thêm hàm này để trừ kho
    @PUT
    @Path("/{id}/reduce")
    @Produces(MediaType.APPLICATION_JSON)
    void reduceStock(@PathParam("id") Long id, @QueryParam("quantity") Integer quantity);
}