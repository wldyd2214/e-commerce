package com.hhplus.commerce.spring.application.order.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreateEvent> kafkaTemplate;

    public void sendOrderCreateEvent(OrderCreateEvent event) {
        kafkaTemplate.send(OrderTopicType.ORDER_CREATED_TOPIC.getTopic(), event);
        log.info("📤 Kafka 이벤트 발행 완료: topic({}), userId({}), orderId({})",
            OrderTopicType.ORDER_CREATED_TOPIC.getTopic(), event.getUserId(), event.getOrderId());
    }
}
