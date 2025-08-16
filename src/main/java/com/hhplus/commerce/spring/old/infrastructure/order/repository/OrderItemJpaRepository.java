package com.hhplus.commerce.spring.old.infrastructure.order.repository;

import com.hhplus.commerce.spring.old.domain.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
}
