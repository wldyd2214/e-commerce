package com.hhplus.commerce.spring.infrastructure.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataPlatformClient {

    public boolean sendOrderData(Long userId, Long orderId) {
        log.info("데이터 플랫폼 주문 내역 전송!");
        return true;
    }
}
