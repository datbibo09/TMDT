package com.ecommerce.payment.domain.model;

public enum ErrorCode {
    PAYMENT_NOT_FOUND(404, "Payment transaction not found"),
    PAYMENT_FAILED(400, "Payment processing failed");

    private final int httpStatus;
    private final String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() { return httpStatus; }
    public String getMessage() { return message; }
}