package com.hhplus.commerce.spring.api.order.event.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.order.event.request.OrderEventRequest;
import com.hhplus.commerce.spring.api.order.model.OrderOutbox;
import com.hhplus.commerce.spring.api.order.model.type.EventStatus;
import com.hhplus.commerce.spring.api.order.repository.OrderOutboxRepository;
import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventScheduler {
    private final ApplicationEventPublisher eventPublisher;
    private final OrderOutboxRepository orderOutboxRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void retrySend() {
        List<OrderOutbox> orderOutboxes =
                orderOutboxRepository.findAllByEventStatus(EventStatus.INIT)
                                     .stream()
                                     .filter(outbox -> LocalDateTime.now()
                                                                    .minusMinutes(5)
                                                                    .isAfter(outbox.getModifiedDateTime()))
                                     .collect(Collectors.toList());

        for (OrderOutbox outbox : orderOutboxes) {
            try {
                OrderEvent orderEvent = objectMapper.readValue(outbox.getPayload(), OrderEvent.class);
                eventPublisher.publishEvent(new OrderEventRequest(orderEvent, outbox.getId()));
                outbox.sendUpdate();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
