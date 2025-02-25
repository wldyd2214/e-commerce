package com.hhplus.commerce.spring.application.order.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderEventConsumer {

    @KafkaListener(topics = "order-created-topic", groupId = "order-group")
    public void listenOrderCreateEvent(OrderCreateEvent event) {
        log.info("📥 Kafka 이벤트 소비 완료: userId({}), orderId({})", event.getUserId(), event.getOrderId());
    }
}
