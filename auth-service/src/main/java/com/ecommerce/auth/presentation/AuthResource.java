package com.ecommerce.auth.presentation;

import com.ecommerce.auth.application.dto.*;
import com.ecommerce.auth.application.service.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/signup")
    public Response signup(@Valid SignupRequest request) {
        authService.signup(request);
        return Response.status(Response.Status.CREATED)
                .entity("Đăng ký thành công")
                .build();
    }

    @POST
    @Path("/signin")
    public Response signin(@Valid LoginRequest request) {
        AuthResponse response = authService.login(request);
        return Response.ok(response).build();
    }

    @POST
    @Path("/refresh")
    public Response refresh(@Valid RefreshTokenRequest request) {
        AuthResponse response = authService.refreshToken(request);
        return Response.ok(response).build();
    }
}