package com.hhplus.commerce.spring.infrastructure.order;

import com.hhplus.commerce.spring.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
