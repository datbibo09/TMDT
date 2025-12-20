package com.ecommerce.auth.application.service;

import com.ecommerce.auth.domain.model.Role;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@ApplicationScoped
public class TokenService {

    @ConfigProperty(name = "smallrye.jwt.sign.key")
    String jwtSecret;

    public String generateAccessToken(String username, Role role, Long userId) {
        return Jwt.issuer("http://localhost:8084")
                .upn(username)
                .groups(new HashSet<>(Arrays.asList(role.name())))
                .claim("userId", userId)
                .expiresIn(3600)
                // SỬA LẠI: Dùng signWithSecret cho chuỗi khóa bí mật
                .signWithSecret(jwtSecret);
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
}