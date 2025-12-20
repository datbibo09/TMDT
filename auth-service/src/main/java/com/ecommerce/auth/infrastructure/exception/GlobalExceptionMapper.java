package com.ecommerce.auth.infrastructure.exception;

import com.ecommerce.auth.domain.exception.BusinessException;
import com.ecommerce.auth.domain.model.ErrorCode;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Throwable exception) {

        // 1. Xử lý lỗi nghiệp vụ (Business Logic)
        if (exception instanceof BusinessException) {
            BusinessException bizEx = (BusinessException) exception;
            LOG.warn("Business Exception: " + bizEx.getMessage());

            ErrorResponse error = new ErrorResponse(
                    bizEx.getErrorCode(),
                    bizEx.getMessage(),
                    uriInfo.getAbsolutePath().toString()
            );

            Response.Status status = Response.Status.BAD_REQUEST; // Default 400
            if (bizEx.getErrorCode() == ErrorCode.LOGIN_FAILED || bizEx.getErrorCode() == ErrorCode.INVALID_TOKEN) {
                status = Response.Status.UNAUTHORIZED; // 401
            } else if (bizEx.getErrorCode() == ErrorCode.USER_ALREADY_EXISTS) {
                status = Response.Status.CONFLICT; // 409
            }

            return Response.status(status).entity(error).build();
        }

        // 2. Xử lý lỗi HTTP chuẩn của Quarkus (Validation, 404, etc.)
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        }

        // 3. Lỗi hệ thống (500)
        LOG.error("System Error: ", exception);
        ErrorResponse error = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                "Hệ thống đang gặp sự cố. Vui lòng thử lại sau.",
                uriInfo.getAbsolutePath().toString()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}