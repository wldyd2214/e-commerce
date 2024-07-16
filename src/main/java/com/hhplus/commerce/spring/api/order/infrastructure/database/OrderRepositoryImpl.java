package com.hhplus.commerce.spring.api.order.infrastructure.database;

import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }
}
