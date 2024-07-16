package com.hhplus.commerce.spring.api.order.infrastructure.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataPlatformClient {

    public boolean sendOrderData(Long userId, Long orderId) {
        log.info(String.format("%d 사용자 %d 주문 정보 데이터 플랫폼 내역 전송!", userId, orderId));
        return true;
    }
}
