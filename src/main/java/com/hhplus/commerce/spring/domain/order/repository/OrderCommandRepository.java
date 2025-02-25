package com.hhplus.commerce.spring.domain.order.repository;

import com.hhplus.commerce.spring.domain.order.model.Order;

public interface OrderCommandRepository {

    Order save(Order order);
}
