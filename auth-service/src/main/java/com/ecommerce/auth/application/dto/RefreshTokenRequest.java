package com.ecommerce.auth.application.dto;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequest {
    @NotBlank(message = "Refresh token không được trống")
    public String refreshToken;
}