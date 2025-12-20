package com.example.orders.domain.exception;

import com.example.orders.domain.model.ErrorCode;

// Nếu bạn muốn dùng 'new BusinessException', hãy xóa chữ 'abstract'
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    // Constructor 1: Chỉ nhận ErrorCode
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // THÊM: Constructor 2: Nhận cả ErrorCode và Message tùy chỉnh
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }
}