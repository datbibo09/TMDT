package com.ecommerce.product.presentation;

import com.ecommerce.product.application.ProductService;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.model.ProductEvent;
import com.ecommerce.product.infrastructure.messaging.ProductEventProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @Inject
    ProductEventProducer productEventProducer;

    @POST
    public Product create(Product product) {
        Product saved = service.create(product);
        productEventProducer.publish(saved, ProductEvent.EventType.CREATED);
        return saved;
    }

    @GET
    public List<Product> getAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Product getById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Product update(@PathParam("id") Long id, Product product) {
        Product updated = service.update(id, product);
        productEventProducer.publish(updated, ProductEvent.EventType.UPDATED);
        return updated;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Product deleted = service.delete(id);
        productEventProducer.publish(deleted, ProductEvent.EventType.DELETED);
    }
}
