package com.ecommerce.gateway.client;

import com.ecommerce.inventory.domain.model.InventoryItem;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/inventory")
@RegisterRestClient(configKey = "inventory-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface InventoryClient {

    @POST
    InventoryItem create(InventoryItem item);

    @GET
    List<InventoryItem> getAll();

    @GET
    @Path("/{id}")
    InventoryItem getById(@PathParam("id") Long id);

    @PUT
    @Path("/{id}/increase")
    InventoryItem increase(
            @PathParam("id") Long id,
            @QueryParam("amount") @DefaultValue("1") int amount
    );

    @PUT
    @Path("/{id}/decrease")
    InventoryItem decrease(
            @PathParam("id") Long id,
            @QueryParam("amount") @DefaultValue("1") int amount
    );
}
