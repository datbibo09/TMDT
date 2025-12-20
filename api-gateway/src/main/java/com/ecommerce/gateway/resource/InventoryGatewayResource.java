package com.ecommerce.gateway.resource;

import com.ecommerce.gateway.client.InventoryClient;
import com.ecommerce.gateway.dto.InventoryRequest;
import com.ecommerce.inventory.domain.model.InventoryItem;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/api/inventory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryGatewayResource {

    @Inject
    @RestClient
    InventoryClient client;

    @POST
    public InventoryItem create(@Valid InventoryRequest request) {
        // Chuyển DTO sang entity
        InventoryItem item = new InventoryItem();
        item.setInventoryCode(request.inventoryCode());
        item.setProductId(request.productId());
        item.setLocation(request.location());
        item.setQuantity(request.quantity());
        return client.create(item);
    }

    @GET
    public List<InventoryItem> getAll() {
        return client.getAll();
    }

    @GET
    @Path("/{id}")
    public InventoryItem getById(@PathParam("id") Long id) {
        return client.getById(id);
    }

    @PUT
    @Path("/{id}/increase")
    public InventoryItem increase(
            @PathParam("id") Long id,
            @QueryParam("amount") @DefaultValue("1") int amount
    ) {
        return client.increase(id, amount);
    }

    @PUT
    @Path("/{id}/decrease")
    public InventoryItem decrease(
            @PathParam("id") Long id,
            @QueryParam("amount") @DefaultValue("1") int amount
    ) {
        return client.decrease(id, amount);
    }
}
