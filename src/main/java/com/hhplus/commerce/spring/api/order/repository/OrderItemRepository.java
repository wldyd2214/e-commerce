package com.hhplus.commerce.spring.api.order.repository;

import com.hhplus.commerce.spring.api.order.model.OrderItem;
import com.hhplus.commerce.spring.api.product.model.Product;
import java.util.List;

public interface OrderItemRepository {

    List<OrderItem> saveAll(List<OrderItem> orderItems);

    List<Product> selectPopularOrderItems();
}
