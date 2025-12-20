package com.example.orders.domain.exception;

import com.example.orders.domain.model.ErrorCode;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(Long id) {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}