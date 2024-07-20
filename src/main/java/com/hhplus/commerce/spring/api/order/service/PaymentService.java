package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.user.infrastructure.client.PaymentSystemClient;
import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.user.model.Payment;
import com.hhplus.commerce.spring.api.user.repository.PaymentRepository;
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
            throw new IllegalArgumentException("결제 실패");
        }

        payment.paymentStatusCompleted();
        paymentRepository.save(payment);

        return payment;
    }
}
