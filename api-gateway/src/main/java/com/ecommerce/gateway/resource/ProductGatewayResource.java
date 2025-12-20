package com.ecommerce.gateway.resource;

import com.ecommerce.gateway.client.ProductClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/products")
@Consumes("application/json")
@Produces("application/json")
public class ProductGatewayResource {

    @Inject
    @RestClient
    ProductClient productClient;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(productClient.getById(id)).build();
    }

    @GET
    public Response getAll() {
        return Response.ok(productClient.getAll()).build();
    }
}
