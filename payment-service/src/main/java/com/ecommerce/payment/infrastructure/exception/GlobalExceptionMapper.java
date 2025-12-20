package com.ecommerce.payment.infrastructure.exception;

import com.ecommerce.payment.domain.exception.BusinessException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof BusinessException be) {
            return Response.status(be.getErrorCode().getHttpStatus())
                    .entity(new ErrorResponse(be.getErrorCode().getHttpStatus(), be.getMessage()))
                    .build();
        }
        return Response.status(500)
                .entity(new ErrorResponse(500, "Internal Server Error: " + exception.getMessage()))
                .build();
    }
}