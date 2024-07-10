package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.entity.OrderItem;
import com.hhplus.commerce.spring.repository.OrderItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJPARepository jpaRepository;

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return jpaRepository.saveAll(orderItems);
    }
}
