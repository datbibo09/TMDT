package com.ecommerce.payment.presentation;

import com.ecommerce.payment.application.PaymentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @GET
    @Path("/order/{orderId}")
    public Response getPaymentByOrder(@PathParam("orderId") Long orderId) {
        // Service sẽ tự throw Exception nếu không tìm thấy
        // GlobalExceptionMapper sẽ bắt và trả về JSON lỗi đẹp
        return Response.ok(paymentService.getPaymentByOrderId(orderId)).build();
    }
}