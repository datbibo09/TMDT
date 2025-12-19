package com.ecommerce.product.domain.exception;

import com.ecommerce.product.domain.model.ErrorCode;

public class InvalidProductPriceException extends BusinessException {

    public InvalidProductPriceException() {
        super(ErrorCode.INVALID_PRODUCT_PRICE, "Giá sản phẩm phải lớn hơn 0");
    }

    public InvalidProductPriceException(double price) {
        super(ErrorCode.INVALID_PRODUCT_PRICE,
                "Giá sản phẩm phải lớn hơn 0, nhưng nhận được: " + price);
    }
}