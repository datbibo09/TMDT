package com.ecommerce.inventory.presentation;

import com.ecommerce.inventory.application.InventoryService;
import com.ecommerce.inventory.domain.model.InventoryItem;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/inventory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @Inject
    InventoryService service;

    @POST
    public InventoryItem create(@Valid InventoryItem item) {
        return service.create(item);
    }

    @GET
    public List<InventoryItem> getAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public InventoryItem getById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}/increase")
    public InventoryItem increase(
            @PathParam("id") Long id,
            @QueryParam("amount") @DefaultValue("1") int amount) {

        return service.increaseQuantity(id, amount);
    }

    @PUT
    @Path("/{id}/decrease")
    public InventoryItem decrease(
            @PathParam("id") Long id,
            @QueryParam("amount") @DefaultValue("1") int amount) {

        return service.decreaseQuantity(id, amount);
    }
}
