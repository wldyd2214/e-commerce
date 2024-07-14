package com.hhplus.commerce.spring.api.service.payment;

import com.hhplus.commerce.spring.infrastructure.payment.PaymentSystemClient;
import com.hhplus.commerce.spring.model.entity.Order;
import com.hhplus.commerce.spring.model.entity.Payment;
import com.hhplus.commerce.spring.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentSystemClient paymentSystemClient;

    public boolean sendPayment(String cardNo, int money) {
        boolean result = paymentSystemClient.sendPayment(cardNo, money);

        if (result) {
            return true;
        }

        return false;
    }

    public Payment paymentUserPoint(Long userId, int payMoney, Order order) {

        boolean extResult = paymentSystemClient.paymentUserPoint(userId, payMoney);

        Payment payment = Payment.create(order, payMoney);

        if (!extResult) {
            payment.paymentStatusFailed();
            paymentRepository.save(payment);

            order.orderStatusPaymentFail();
            throw new IllegalArgumentException("결제 실패");
        }

        payment.paymentStatusCompleted();
        paymentRepository.save(payment);

        return payment;
    }
}
