package com.ecommerce.product.infrastructure.exception;

import com.ecommerce.product.domain.exception.BusinessException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<BusinessException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(BusinessException exception) {
        ErrorResponse error = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                uriInfo.getAbsolutePath().toString()
        );

        int status = switch (exception.getErrorCode()) {
            case PRODUCT_ALREADY_EXISTS -> 409;  // Conflict - khi trùng dữ liệu sau này
            case PRODUCT_NOT_FOUND      -> 404;  // không vào đây vì có mapper riêng ưu tiên cao hơn
            default                     -> 400;  // Bad Request: name, price, stock invalid
        };

        return Response.status(status)
                .entity(error)
                .build();
    }
}