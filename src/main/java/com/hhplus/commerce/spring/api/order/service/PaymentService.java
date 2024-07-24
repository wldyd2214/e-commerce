package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.user.infrastructure.client.PaymentSystemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentSystemClient paymentSystemClient;

    public boolean sendPayment(String cardNo, int money) {
        boolean result = paymentSystemClient.sendPayment(cardNo, money);

        if (result) {
            return true;
        }

        return false;
    }
}
