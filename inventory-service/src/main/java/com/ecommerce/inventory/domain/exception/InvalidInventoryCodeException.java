package com.ecommerce.inventory.domain.exception;

public class InvalidInventoryCodeException extends RuntimeException {
    public InvalidInventoryCodeException(String message) {
        super(message);
    }
}