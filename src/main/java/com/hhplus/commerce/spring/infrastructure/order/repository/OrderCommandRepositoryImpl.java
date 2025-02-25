package com.hhplus.commerce.spring.infrastructure.order.repository;

import com.hhplus.commerce.spring.domain.order.model.Order;
import com.hhplus.commerce.spring.domain.order.repository.OrderCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderCommandRepositoryImpl implements OrderCommandRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }
}
