package com.ecommerce.auth.infrastructure.exception;

import com.ecommerce.auth.domain.model.ErrorCode;
import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp,
        String path
) {
    public ErrorResponse(ErrorCode errorCode, String message, String path) {
        this(errorCode.getCode(), message, LocalDateTime.now(), path);
    }

    // Constructor cho lỗi hệ thống chung
    public ErrorResponse(String code, String message, String path) {
        this(code, message, LocalDateTime.now(), path);
    }
}