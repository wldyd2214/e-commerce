package com.hhplus.commerce.spring.api.order.infrastructure.database;

import com.hhplus.commerce.spring.api.order.model.OrderOutbox;
import com.hhplus.commerce.spring.api.order.model.type.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderOutboxJpaRepository extends JpaRepository<OrderOutbox, Long> {
    List<OrderOutbox> findAllByEventStatus(EventStatus status);
}
