package com.ecommerce.product.domain.exception;

import com.ecommerce.product.domain.model.ErrorCode;

public class InvalidProductStockException extends BusinessException {

    public InvalidProductStockException() {
        super(ErrorCode.INVALID_PRODUCT_STOCK, "Số lượng tồn kho không hợp lệ");
    }

    public InvalidProductStockException(int quantity) {
        super(ErrorCode.INVALID_PRODUCT_STOCK,
                "Số lượng tồn kho phải >= 0, nhưng nhận được: " + quantity);
    }
}