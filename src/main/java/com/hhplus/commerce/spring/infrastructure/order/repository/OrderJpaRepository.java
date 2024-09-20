package com.hhplus.commerce.spring.infrastructure.order.repository;

import com.hhplus.commerce.spring.infrastructure.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
