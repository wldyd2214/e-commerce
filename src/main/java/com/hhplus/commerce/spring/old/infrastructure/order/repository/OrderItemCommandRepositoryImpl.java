package com.hhplus.commerce.spring.old.infrastructure.order.repository;

import com.hhplus.commerce.spring.old.domain.order.model.OrderItem;
import com.hhplus.commerce.spring.old.domain.order.repository.OrderItemCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderItemCommandRepositoryImpl implements OrderItemCommandRepository {

    private final OrderItemJpaRepository jpaRepository;

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return jpaRepository.saveAll(orderItems);
    }
}
