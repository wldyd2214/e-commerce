package com.hhplus.commerce.spring.api.service;

import com.hhplus.commerce.spring.infrastructure.pay.PaySystemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PaySystemClient paySystemClient;

    public boolean sendPayment(String cardNo, int money) {
        boolean result = paySystemClient.sendPayment(cardNo, money);

        if (result) {
            return true;
        }

        return false;
    }

}
