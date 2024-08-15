package com.hhplus.commerce.spring.api.order.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.order.event.request.OrderEventRequest;
import com.hhplus.commerce.spring.api.order.repository.OrderOutboxRepository;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Slf4j
public class OutboxConsumer {
    private final OrderOutboxRepository orderOutboxRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order_complete_topic", groupId = "order-group-2", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void outBox(ConsumerRecord<String, String> consumerRecord) {
        try {
            OrderEventRequest orderEventRequest = objectMapper.readValue(consumerRecord.value().toString(), OrderEventRequest.class);
            log.info("OutboxConsumer - outBox() consumerRecord : {} ", consumerRecord.value());
            orderOutboxRepository.findById(orderEventRequest.getMessageId()).published();
        }  catch (JsonProcessingException ex) {
            log.error("OutboxConsumer - JsonProcessingException: {}", ex.getMessage());
            ex.getMessage();
        }
    }
}
