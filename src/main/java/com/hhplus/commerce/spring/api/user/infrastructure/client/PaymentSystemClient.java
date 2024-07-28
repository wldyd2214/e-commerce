package com.hhplus.commerce.spring.api.user.infrastructure.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentSystemClient {

    public boolean sendPayment(String cardNo, int money) {
        log.info("carNo: {} {}원 결제 요청", cardNo, money);
        return true;
    }
}
