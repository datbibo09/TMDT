package com.ecommerce.product.infrastructure.exception;

import com.ecommerce.product.domain.model.ErrorCode;
import jakarta.ws.rs.WebApplicationException; // <--- Thêm import này
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

        // 1. Kiểm tra xem lỗi có phải là lỗi HTTP chuẩn (như 404, 400, 401...) không
        if (exception instanceof WebApplicationException) {
            // Nếu đúng, trả về nguyên bản lỗi đó (Ví dụ: trả về 404 thay vì 500)
            return ((WebApplicationException) exception).getResponse();
        }

        // 2. Nếu không phải lỗi Web (ví dụ NullPointer, lỗi DB...), thì mới coi là lỗi hệ thống 500
        LOG.error("Unhandled exception: " + exception.getMessage(), exception);

        ErrorResponse error = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR,
                "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.",
                uriInfo.getAbsolutePath().toString()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}