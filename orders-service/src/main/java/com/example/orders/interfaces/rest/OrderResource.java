package com.example.orders.interfaces.rest;

import com.example.orders.application.dto.OrderRequest;
import com.example.orders.application.dto.OrderResponse;
import com.example.orders.application.service.OrderService;
import com.example.orders.domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    public Response createOrder(OrderRequest request) {
        Order response = orderService.createOrder(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GET
    @Path("/{id}")
    public OrderResponse getOrderById(@PathParam("id") Long id) {
        return orderService.getOrderById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderService.deleteOrder(id);
        return Response.noContent().build();
    }
}