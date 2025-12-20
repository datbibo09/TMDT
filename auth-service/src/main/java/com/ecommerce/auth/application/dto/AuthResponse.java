package com.ecommerce.auth.application.dto;

public class AuthResponse {
    public String accessToken;
    public String refreshToken;
    public String role;
    public Long userId;

    public AuthResponse(String accessToken, String refreshToken, String role, Long userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
        this.userId = userId;
    }
}