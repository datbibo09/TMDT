package com.example.orders.infrastructure.exception;

import com.example.orders.domain.exception.BusinessException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof BusinessException be) {
            return Response.status(be.getErrorCode().getHttpStatus())
                    .entity(new ErrorResponse(be.getErrorCode().getHttpStatus(), be.getMessage()))
                    .build();
        }

        LOG.error("Unhandled exception: ", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(500, "An unexpected error occurred: " + exception.getMessage()))
                .build();
    }
}