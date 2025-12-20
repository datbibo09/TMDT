package com.ecommerce.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "orders-api")
@Path("/orders")
public interface OrderClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Object createOrder(Object orderRequest);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Object getAllOrders();

    @GET
    @Path("/{id}")
    Object getOrderById(@PathParam("id") Long id);
}