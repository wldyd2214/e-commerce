package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.entity.Order;

public interface OrderRepository {

    Order save(Order order);
}
