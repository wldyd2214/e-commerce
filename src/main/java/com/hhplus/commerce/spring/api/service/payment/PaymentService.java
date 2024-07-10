package com.hhplus.commerce.spring.api.service.payment;

import com.hhplus.commerce.spring.infrastructure.payment.PaymentSystemClient;
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

    public boolean pointPayment(long userId, int userPoint, int payMoney) {
        boolean result = paymentSystemClient.pointPayment(userId, userPoint, payMoney);

        if (result) {
            return true;
        }

        return false;
    }
}
