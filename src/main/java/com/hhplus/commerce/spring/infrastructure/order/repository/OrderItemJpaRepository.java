package com.hhplus.commerce.spring.infrastructure.order.repository;

import com.hhplus.commerce.spring.domain.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
}
