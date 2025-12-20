package com.ecommerce.inventory.infrastructure.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider  // Quan trọng: Quarkus sẽ tự register class này
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        // Các lỗi thường gặp trả 400 Bad Request
        if (exception instanceof jakarta.validation.ConstraintViolationException ||
                exception.getMessage().contains("đã tồn tại") ||
                exception.getMessage().contains("không đủ") ||
                exception.getMessage().contains("phải > 0")) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", exception.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Không tìm thấy → 404
        if (exception.getMessage().contains("Không tìm thấy")) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", exception.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Lỗi mặc định → 500, không lộ stack trace ra client
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Map.of("error", "Lỗi hệ thống, vui lòng thử lại sau"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}