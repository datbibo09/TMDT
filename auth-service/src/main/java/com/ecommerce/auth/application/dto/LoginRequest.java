package com.ecommerce.auth.application.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Username bắt buộc nhập")
    public String username;

    @NotBlank(message = "Password bắt buộc nhập")
    public String password;
}