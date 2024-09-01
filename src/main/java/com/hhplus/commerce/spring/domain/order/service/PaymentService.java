package com.hhplus.commerce.spring.domain.order.service;

import com.hhplus.commerce.spring.old.api.user.infrastructure.client.PaymentSystemClient;
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
