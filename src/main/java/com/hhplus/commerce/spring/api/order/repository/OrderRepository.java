package com.hhplus.commerce.spring.api.order.repository;

import com.hhplus.commerce.spring.api.order.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
