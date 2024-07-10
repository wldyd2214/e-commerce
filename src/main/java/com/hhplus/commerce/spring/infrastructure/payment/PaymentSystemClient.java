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

    public boolean pointPayment(long userId, int userPoint, int payMoney) {
        log.info("%d 사용자 %d 포인트 결제 요청", userId, payMoney);
        log.info("%d 사용자 잔여 포인트 %d", userId, (userPoint - payMoney));
        return true;
    }
}
