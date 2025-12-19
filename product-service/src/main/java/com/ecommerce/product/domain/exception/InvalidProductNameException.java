package com.ecommerce.product.domain.exception;

import com.ecommerce.product.domain.model.ErrorCode;

public class InvalidProductNameException extends BusinessException {

    public InvalidProductNameException() {
        super(ErrorCode.INVALID_PRODUCT_NAME, "Tên sản phẩm không được để trống");
    }

    public InvalidProductNameException(String name) {
        super(ErrorCode.INVALID_PRODUCT_NAME,
                "Tên sản phẩm không hợp lệ: '" + name + "'");
    }
}