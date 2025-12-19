package com.ecommerce.product.domain.exception;

import com.ecommerce.product.domain.model.ErrorCode;

public class ProductAlreadyExistsException extends BusinessException {

    public ProductAlreadyExistsException(String field, String value) {
        super(ErrorCode.PRODUCT_ALREADY_EXISTS,
                "Sản phẩm với " + field + " = '" + value + "' đã tồn tại");
    }

    // Trường hợp đơn giản
    public ProductAlreadyExistsException() {
        super(ErrorCode.PRODUCT_ALREADY_EXISTS,
                "Sản phẩm đã tồn tại");
    }

    // Message tùy chỉnh
    public ProductAlreadyExistsException(String message) {
        super(ErrorCode.PRODUCT_ALREADY_EXISTS, message);
    }
}