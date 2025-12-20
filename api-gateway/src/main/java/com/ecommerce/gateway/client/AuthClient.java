package com.ecommerce.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "auth-api")
@Path("/auth")
public interface AuthClient {
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    Object login(Object loginRequest);

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    Object signup(Object signupRequest);
}