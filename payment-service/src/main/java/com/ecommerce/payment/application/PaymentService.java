package com.ecommerce.payment.application;

import com.ecommerce.payment.domain.exception.PaymentNotFoundException;
import com.ecommerce.payment.domain.model.Payment;
import com.ecommerce.payment.domain.model.PaymentStatus;
import com.ecommerce.payment.domain.repository.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    @Inject
    @Channel("payment-out")
    Emitter<Payment> paymentEmitter;

    @Transactional
    public void processPayment(Long orderId, Double amount) {
        System.out.println("Processing payment for Order ID: " + orderId);

        Payment payment = Payment.create(orderId, amount);

        // Giả lập logic thanh toán
        // Nếu số tiền quá lớn (> 100 triệu) -> Từ chối
        if (amount != null && amount > 100_000_000) {
            payment.status = PaymentStatus.FAILED;
        } else {
            payment.status = PaymentStatus.COMPLETED;
        }

        // Lưu DB
        paymentRepository.persist(payment);

        // Bắn event ra RabbitMQ để các service khác biết
        paymentEmitter.send(payment).whenComplete((success, error) -> {
            if (error != null) {
                System.out.println("Failed to send payment event: " + error.getMessage());
            }
        });
    }

    public Payment getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            throw new PaymentNotFoundException(orderId);
        }
        return payment;
    }
}