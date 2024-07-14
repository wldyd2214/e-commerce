package com.hhplus.commerce.spring.infrastructure.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentSystemClient {

    public boolean sendPayment(String cardNo, int money) {
        log.info("결제 요청");
        return true;
    }

    public boolean paymentUserPoint(long userId, int payMoney) {
        log.info(String.format("%d 사용자 %d 포인트 결제 요청", userId, payMoney));
        return true;
    }
}
