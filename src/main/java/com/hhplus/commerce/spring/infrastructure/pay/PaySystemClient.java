package com.hhplus.commerce.spring.infrastructure.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaySystemClient {

    public boolean sendPayment(String cardNo, int money) {
        log.info("결제 요청");
        return true;
    }
}
