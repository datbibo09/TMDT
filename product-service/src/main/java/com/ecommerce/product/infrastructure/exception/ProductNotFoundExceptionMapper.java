package com.ecommerce.product.infrastructure.exception;

import com.ecommerce.product.domain.exception.ProductNotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ProductNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                uriInfo.getAbsolutePath().toString()
        );

        return Response.status(Response.Status.NOT_FOUND)  // 404
                .entity(error)
                .build();
    }
}