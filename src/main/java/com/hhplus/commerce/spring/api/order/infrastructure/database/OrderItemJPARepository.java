package com.hhplus.commerce.spring.api.order.infrastructure.database;

import com.hhplus.commerce.spring.api.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJPARepository extends JpaRepository<OrderItem, Long> {
}
