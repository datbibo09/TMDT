package com.ecommerce.auth.domain.exception;

import com.ecommerce.auth.domain.model.ErrorCode;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException(String message) {
        super(ErrorCode.USER_ALREADY_EXISTS, message);
    }
}