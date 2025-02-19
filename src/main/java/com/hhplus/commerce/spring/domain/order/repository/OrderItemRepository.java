package com.hhplus.commerce.spring.domain.order.repository;

import com.hhplus.commerce.spring.domain.order.model.OrderItem;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;

public interface OrderItemRepository {

    List<OrderItem> saveAll(List<OrderItem> orderItems);

    List<Product> selectPopularOrderItems();
}
