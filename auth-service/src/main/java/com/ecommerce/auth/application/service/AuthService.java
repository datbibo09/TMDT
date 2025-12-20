package com.ecommerce.auth.application.service;

import com.ecommerce.auth.application.dto.*;
import com.ecommerce.auth.domain.exception.*;
import com.ecommerce.auth.domain.model.ErrorCode;
import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuthService {

    @Inject
    TokenService tokenService;

    @Transactional
    public User signup(SignupRequest req) {
        // 1. Logic Validation
        if (req.username.equals(req.password)) {
            throw new InvalidRequestException("Username và Password không được giống nhau");
        }

        if (req.gender == null ||
                (!"NAM".equalsIgnoreCase(req.gender) && !"NU".equalsIgnoreCase(req.gender))) {
            throw new InvalidRequestException("Giới tính chỉ được nhập 'NAM' hoặc 'NU'");
        }

        if (User.findByUsername(req.username) != null) {
            throw new UserAlreadyExistsException("Username '" + req.username + "' đã tồn tại");
        }

        // 2. Create User
        User user = new User();
        user.username = req.username;
        user.password = BcryptUtil.bcryptHash(req.password);
        user.fullName = req.fullName;
        user.gender = req.gender.toUpperCase();
        user.birthday = req.birthday;
        user.role = Role.USER;

        user.persist();
        return user;
    }

    @Transactional
    public AuthResponse login(LoginRequest req) {
        User user = User.findByUsername(req.username);

        if (user == null || !BcryptUtil.matches(req.password, user.password)) {
            throw new AuthFailedException("Sai thông tin đăng nhập");
        }

        String accessToken = tokenService.generateAccessToken(user.username, user.role, user.id);
        String refreshToken = tokenService.generateRefreshToken();

        user.refreshToken = refreshToken;
        user.persist();

        return new AuthResponse(accessToken, refreshToken, user.role.name(), user.id);
    }

    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest req) {
        User user = User.findByRefreshToken(req.refreshToken);
        if (user == null) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN, "Refresh token không hợp lệ") {};
        }

        String newAccessToken = tokenService.generateAccessToken(user.username, user.role, user.id);

        // (Tuỳ chọn) Tạo Refresh Token mới để xoay vòng (Rotating Refresh Token)
        // String newRefreshToken = tokenService.generateRefreshToken();
        // user.refreshToken = newRefreshToken;
        // user.persist();

        return new AuthResponse(newAccessToken, user.refreshToken, user.role.name(), user.id);
    }
}