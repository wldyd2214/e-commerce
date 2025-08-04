package com.hhplus.commerce.spring.old.domain.order.repository;

import com.hhplus.commerce.spring.old.domain.order.model.OrderItem;
import java.util.List;

public interface OrderItemCommandRepository {

    List<OrderItem> saveAll(List<OrderItem> orderItems);
}
