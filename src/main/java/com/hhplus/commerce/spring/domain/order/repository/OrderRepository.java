package com.hhplus.commerce.spring.domain.order.repository;

import com.hhplus.commerce.spring.domain.order.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
