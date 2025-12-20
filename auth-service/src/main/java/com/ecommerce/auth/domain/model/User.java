package com.ecommerce.auth.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String username;

    @Column(nullable = false)
    public String password; // Lưu hash

    public String fullName;

    public String gender; // "NAM" hoặc "NU"

    public LocalDate birthday;

    @Enumerated(EnumType.STRING)
    public Role role;

    @Column(length = 1000) // Token có thể dài
    public String refreshToken;

    // Helper methods
    public static User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public static User findByRefreshToken(String token) {
        return find("refreshToken", token).firstResult();
    }
}