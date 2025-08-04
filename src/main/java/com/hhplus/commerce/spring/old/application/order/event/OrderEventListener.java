package com.hhplus.commerce.spring.old.application.order.event;

import com.hhplus.commerce.spring.old.infrastructure.order.client.DataPlatformClient;
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

    /**
     * 성능 최적화를 위한 Async 어노테이션 선언
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendOrderData(OrderCreateEvent createEvent) {
        boolean dataResult = dataPlatformClient.sendOrderData(createEvent.getUserId(), createEvent.getOrderId());
        log.info("[OrderEventListener.sendOrderData()] - 데이터 플랫폼 전송 결과 : {} ", dataResult);
    }
}
