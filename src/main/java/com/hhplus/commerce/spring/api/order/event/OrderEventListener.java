package com.hhplus.commerce.spring.api.order.event;

import com.hhplus.commerce.spring.api.order.event.publisher.KafkaPublisher;
import com.hhplus.commerce.spring.api.order.event.request.OrderEventRequest;
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
    private final KafkaPublisher kafkaPublisher;

    @Async
    //BEFORE_COMMIT, AFTER_COMMIT 가 어떤 동작을 하는지 알아야 한다!
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendOrderData(OrderEventRequest orderEventRequest) {
        log.info("sendOrderData() - 이벤트 발행!");
        kafkaPublisher.publisherOrderInfo("order_complete_topic", orderEventRequest);
    }
}
