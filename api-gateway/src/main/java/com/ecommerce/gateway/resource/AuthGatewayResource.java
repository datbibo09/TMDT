package com.ecommerce.gateway.resource;

import com.ecommerce.gateway.client.AuthClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gateway/auth") // Đường dẫn mà Frontend sẽ gọi vào
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthGatewayResource {

    @Inject
    @RestClient
    AuthClient authClient; // Inject Interface gọi sang Auth-Service

    @POST
    @Path("/login")
    public Response login(Object loginRequest) {
        try {
            // Chuyển tiếp yêu cầu đăng nhập sang Auth-Service
            Object response = authClient.login(loginRequest);
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Đăng nhập thất bại: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/signup")
    public Response signup(Object signupRequest) {
        try {
            // Chuyển tiếp yêu cầu đăng ký sang Auth-Service
            Object response = authClient.signup(signupRequest);
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Đăng ký thất bại: " + e.getMessage()).build();
        }
    }
}