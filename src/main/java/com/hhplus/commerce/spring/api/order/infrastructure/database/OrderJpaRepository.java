package com.hhplus.commerce.spring.api.order.infrastructure.database;

import com.hhplus.commerce.spring.api.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
