package com.ecommerce.auth.domain.exception;

import com.ecommerce.auth.domain.model.ErrorCode;

public class AuthFailedException extends BusinessException {
    public AuthFailedException(String message) {
        super(ErrorCode.LOGIN_FAILED, message);
    }
}