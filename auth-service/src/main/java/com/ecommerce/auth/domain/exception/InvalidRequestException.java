package com.ecommerce.auth.domain.exception;

import com.ecommerce.auth.domain.model.ErrorCode;

public class InvalidRequestException extends BusinessException {
    public InvalidRequestException(String message) {
        super(ErrorCode.INVALID_REQUEST, message);
    }
}