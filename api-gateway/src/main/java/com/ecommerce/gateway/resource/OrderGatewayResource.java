package com.ecommerce.gateway.resource;

import com.ecommerce.gateway.client.OrderClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gateway/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderGatewayResource {

    @Inject
    @RestClient
    OrderClient orderClient;

    @POST
    public Response createOrder(Object request) {
        // Gateway nhận request và forward sang Orders-Service
        return Response.status(201).entity(orderClient.createOrder(request)).build();
    }

    @GET
    public Response getAll() {
        return Response.ok(orderClient.getAllOrders()).build();
    }
}