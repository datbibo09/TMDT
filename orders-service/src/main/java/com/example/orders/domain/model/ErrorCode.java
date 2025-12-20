package com.example.orders.domain.model;

public enum ErrorCode {
    ORDER_NOT_FOUND(404, "Order not found"),
    INSUFFICIENT_STOCK(400, "Product is out of stock"),
    PRODUCT_NOT_FOUND(404, "Product not found in system"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int httpStatus;
    private final String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() { return httpStatus; }
    public String getMessage() { return message; }
}