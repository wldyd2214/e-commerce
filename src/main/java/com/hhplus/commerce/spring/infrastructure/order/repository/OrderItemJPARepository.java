package com.hhplus.commerce.spring.infrastructure.order.repository;

import com.hhplus.commerce.spring.infrastructure.order.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJPARepository extends JpaRepository<OrderItemEntity, Long> {
}
