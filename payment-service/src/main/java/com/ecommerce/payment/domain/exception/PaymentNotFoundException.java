package com.ecommerce.payment.domain.exception;

import com.ecommerce.payment.domain.model.ErrorCode;

public class PaymentNotFoundException extends BusinessException {
    public PaymentNotFoundException(Long orderId) {
        super(ErrorCode.PAYMENT_NOT_FOUND); // Có thể thêm message custom nếu muốn
    }
}