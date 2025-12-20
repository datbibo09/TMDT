package com.ecommerce.gateway.client;

import com.ecommerce.gateway.dto.ProductRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "product-api")
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProductClient {


    // Lấy sản phẩm theo ID (trả JSON dưới dạng String)
    @GET
    @Path("/{id}")
    String getById(@PathParam("id") Long id);

    // Lấy tất cả sản phẩm (trả JSON dưới dạng String)
    @GET
    String getAll();
}
