package com.hhplus.commerce.spring.domain.order.event;

import com.hhplus.commerce.spring.application.order.event.OrderCreateEvent;
import com.hhplus.commerce.spring.domain.product.service.ProductStockService;
import com.hhplus.commerce.spring.domain.user.service.UserPointService;
import com.hhplus.commerce.spring.infrastructure.order.client.DataPlatformClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderEventListener {

    private final ProductStockService stockService;
    private final UserPointService userPointService;
    private final DataPlatformClient dataPlatformClient;

    /**
     * 성능 최적화를 위한 Async 어노테이션 선언
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void orderCreatedEvent(OrderCreateEvent event) {
        log.info("\uD83D\uDCE2 orderCreatedEvent 감지됨!");

        // 1. 상품 서비스의 상품 재고 차감 메소드 호출
        stockService.deductStock(event.getStockDeductionInfo());

        // 2. 사용자 서비스의 사용자 포인트 차감 메소드 호출
        userPointService.deductPoints(event.getPointDeductionInfo());

        // 3. 외부 시스템 주문 생성 호출
        boolean dataResult = dataPlatformClient.sendOrderData(event.getUserId(), event.getOrderId());
        log.info("[OrderEventListener.sendOrderData()] - 데이터 플랫폼 전송 결과 : {} ", dataResult);
    }
}
