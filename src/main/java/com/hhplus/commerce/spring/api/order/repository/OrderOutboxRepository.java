package com.hhplus.commerce.spring.api.order.repository;

import com.hhplus.commerce.spring.api.order.model.OrderOutbox;
import com.hhplus.commerce.spring.api.order.model.type.EventStatus;

import java.util.List;
import java.util.Optional;

public interface OrderOutboxRepository {
    OrderOutbox save(OrderOutbox orderOutbox);
    OrderOutbox findById(Long outboxId);
    List<OrderOutbox> findCreatedEventsOlderThanFiveMinutes();

    List<OrderOutbox> findAllByEventStatus(EventStatus status);
}
