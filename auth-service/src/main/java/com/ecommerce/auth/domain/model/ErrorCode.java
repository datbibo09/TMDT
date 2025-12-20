package com.ecommerce.auth.domain.model;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("AUTH_000", "Lỗi hệ thống"),
    INVALID_REQUEST("AUTH_001", "Dữ liệu yêu cầu không hợp lệ"),
    USER_ALREADY_EXISTS("AUTH_002", "Người dùng đã tồn tại"),
    LOGIN_FAILED("AUTH_003", "Sai tên đăng nhập hoặc mật khẩu"),
    INVALID_TOKEN("AUTH_004", "Refresh token không hợp lệ hoặc đã hết hạn");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}