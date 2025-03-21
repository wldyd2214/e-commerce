package com.hhplus.commerce.spring.infrastructure.order.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataPlatformClient {

    public boolean sendOrderData(Long userId, Long orderId) {
        log.info("[DataPlatformClient.sendOrderData()] - {} 사용자 {} 주문 정보 데이터 플랫폼 내역 전송!", userId, orderId);
        return true;
    }
}
