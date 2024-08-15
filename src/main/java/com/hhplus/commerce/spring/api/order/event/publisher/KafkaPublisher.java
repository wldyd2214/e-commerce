package com.hhplus.commerce.spring.api.order.event.publisher;

import com.hhplus.commerce.spring.api.order.event.request.OrderEventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaPublisher {
    private final KafkaTemplate<String, OrderEventRequest> kafkaTemplate;

    public void publisherOrderInfo(String topic, OrderEventRequest orderEvent) {
        log.info("publisherOrderInfo() - topic={}, messageId={}", topic, orderEvent.getMessageId());
        kafkaTemplate.send(topic, orderEvent);
    }
}
