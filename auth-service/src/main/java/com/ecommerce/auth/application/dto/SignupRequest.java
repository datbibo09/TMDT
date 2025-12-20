package com.ecommerce.auth.application.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class SignupRequest {
    @NotBlank(message = "Họ tên không được để trống")
    public String fullName;

    @NotBlank(message = "Username không được để trống")
    public String username;

    @NotBlank(message = "Password không được để trống")
    public String password;

    public String gender;
    public LocalDate birthday;
}