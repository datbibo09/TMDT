package com.ecommerce.product.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
public enum ErrorCode {

    // ====================== COMMON ERRORS ======================
    INVALID_REQUEST("INVALID_REQUEST"),             // Dữ liệu đầu vào không hợp lệ (validation chung)
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"), // Lỗi hệ thống không mong muốn

    // ====================== PRODUCT ERRORS ======================
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND"),                 // Không tìm thấy sản phẩm
    PRODUCT_ALREADY_EXISTS("PRODUCT_ALREADY_EXISTS"),       // Sản phẩm đã tồn tại (trùng name/sku/code...)

    INVALID_PRODUCT_NAME("INVALID_PRODUCT_NAME"),           // Tên sản phẩm không hợp lệ (rỗng, null, quá dài...)
    INVALID_PRODUCT_PRICE("INVALID_PRODUCT_PRICE"),         // Giá sản phẩm không hợp lệ (≤ 0, âm...)
    INVALID_PRODUCT_STOCK("INVALID_PRODUCT_STOCK");         // Số lượng tồn kho không hợp lệ (< 0...)

    // Nếu sau này cần thêm:
    // PRODUCT_CANNOT_DELETE("PRODUCT_CANNOT_DELETE"),      // Không thể xóa vì có đơn hàng liên quan
    // UNAUTHORIZED("UNAUTHORIZED"),
    // FORBIDDEN("FORBIDDEN"),

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    /**
     * Jackson sẽ serialize enum ra đúng chuỗi code (ví dụ: "PRODUCT_NOT_FOUND")
     * thay vì tên enum hoặc object phức tạp.
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    // Optional: tiện cho việc tìm ErrorCode từ String (nếu cần log/monitor)
    public static ErrorCode fromCode(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code.equals(code)) {
                return errorCode;
            }
        }
        return null;
    }
}