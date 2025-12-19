package com.ecommerce.product.infrastructure.exception;

import com.ecommerce.product.domain.model.ErrorCode;

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

    public ErrorResponse(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }
}