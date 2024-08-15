package com.hhplus.commerce.spring.api.order.infrastructure.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.api.order.model.OrderOutbox;
import com.hhplus.commerce.spring.api.order.model.type.EventStatus;
import com.hhplus.commerce.spring.api.order.repository.OrderOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderOutboxRepositoryImpl implements OrderOutboxRepository {
    private final OrderOutboxJpaRepository jpaRepository;

    @Override
    public OrderOutbox save(OrderOutbox orderOutbox) {
        return jpaRepository.save(orderOutbox);
    }

    @Override
    public OrderOutbox findById(Long outboxId) {
        return jpaRepository.findById(outboxId)
                            .orElseThrow(() -> new CustomBadRequestException(BadRequestErrorCode.ORDER_OUTBOX_BAD_REQUEST));
    }

    @Override
    public List<OrderOutbox> findCreatedEventsOlderThanFiveMinutes() {
        return null;
    }

    @Override
    public List<OrderOutbox> findAllByEventStatus(EventStatus status) {
        return jpaRepository.findAllByEventStatus(status);
    }
}
