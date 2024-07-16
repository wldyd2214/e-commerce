package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.entity.OrderItem;
import com.hhplus.commerce.spring.model.entity.Product;
import java.util.List;

public interface OrderItemRepository {

    List<OrderItem> saveAll(List<OrderItem> orderItems);

    List<Product> selectPopularOrderItems();
}
