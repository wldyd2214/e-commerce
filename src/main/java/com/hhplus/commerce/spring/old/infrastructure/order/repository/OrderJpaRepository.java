package com.hhplus.commerce.spring.old.infrastructure.order.repository;

import com.hhplus.commerce.spring.old.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
