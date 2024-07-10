package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
