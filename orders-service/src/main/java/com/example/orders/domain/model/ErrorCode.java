package com.example.orders.domain.model;

public enum ErrorCode {
    ORDER_NOT_FOUND(404, "Không tìm thấy đơn hàng trong hệ thống"),
    INSUFFICIENT_STOCK(400, "Sản phẩm trong kho không đủ số lượng"),
    PRODUCT_NOT_FOUND(404, "Sản phẩm không tồn tại hoặc lỗi kết nối kho"),
    DATABASE_ERROR(500, "Lỗi truy xuất cơ sở dữ liệu");

    private final int httpStatus;
    private final String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() { return httpStatus; }
    public String getMessage() { return message; }
}