package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.order.infrastructure.client.DataPlatformClient;
import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderEventListener {

    private final DataPlatformClient dataPlatformClient;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendOrderData(OrderEvent orderEvent) {
        boolean dataResult = dataPlatformClient.sendOrderData(orderEvent.getUserId(), orderEvent.getOrderId());
        log.info("데이터 플랫폼 전송 결과 : {} ", dataResult);
    }
}
