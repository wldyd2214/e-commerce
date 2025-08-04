package com.hhplus.commerce.spring.old.domain.order.repository;

import com.hhplus.commerce.spring.old.domain.order.model.Order;

public interface OrderCommandRepository {

    Order save(Order order);
}
