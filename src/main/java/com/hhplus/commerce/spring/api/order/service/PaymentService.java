package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomConflictException;
import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.user.infrastructure.client.PaymentSystemClient;
import com.hhplus.commerce.spring.api.user.model.Payment;
import com.hhplus.commerce.spring.api.user.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadGateWayErrorCode.PAYMENT_BAD_GATEWAY;

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
            throw new CustomBadGateWayException(PAYMENT_BAD_GATEWAY);
        }

        payment.paymentStatusCompleted();
        paymentRepository.save(payment);

        return payment;
    }
}
