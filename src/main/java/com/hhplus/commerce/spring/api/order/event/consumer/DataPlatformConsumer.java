package com.hhplus.commerce.spring.api.order.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.order.event.request.OrderEventRequest;
import com.hhplus.commerce.spring.api.order.infrastructure.client.DataPlatformClient;
import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class DataPlatformConsumer {
    private final DataPlatformClient dataPlatformClient;
    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order_complete_topic", groupId = "order-group-1", containerFactory = "kafkaListenerContainerFactory")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        try {
            log.info("DataPlatformConsumer - receive() consumerRecord : {} ", consumerRecord.value());

            OrderEventRequest orderEventRequest = objectMapper.readValue(consumerRecord.value().toString(), OrderEventRequest.class);
            OrderEvent event = orderEventRequest.getOrderEvent();

            boolean dataResult = dataPlatformClient.sendOrderData(event.getUserId(), event.getOrderId());
            log.info("데이터 플랫폼 전송 결과 : {} ", dataResult);
        }  catch (JsonProcessingException ex) {
            log.error("OutboxConsumer - JsonProcessingException: {}", ex.getMessage());
            ex.getMessage();
        }
    }
}
