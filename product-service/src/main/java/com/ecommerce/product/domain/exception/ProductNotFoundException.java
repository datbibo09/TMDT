package com.ecommerce.product.domain.exception;

import com.ecommerce.product.domain.model.ErrorCode;

public class ProductNotFoundException extends BusinessException {

    public ProductNotFoundException(Long id) {
        super(ErrorCode.PRODUCT_NOT_FOUND,
                "Không tìm thấy sản phẩm với id = " + id);
    }

    // Linh hoạt cho tìm theo field khác (sku, code...)
    public ProductNotFoundException(String field, String value) {
        super(ErrorCode.PRODUCT_NOT_FOUND,
                "Không tìm thấy sản phẩm với " + field + " = '" + value + "'");
    }

    // Message tùy chỉnh
    public ProductNotFoundException(String message) {
        super(ErrorCode.PRODUCT_NOT_FOUND, message);
    }
}