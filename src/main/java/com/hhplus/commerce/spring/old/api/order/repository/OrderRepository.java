package com.hhplus.commerce.spring.old.api.order.repository;

import com.hhplus.commerce.spring.domain.order.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
