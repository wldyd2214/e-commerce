package com.hhplus.commerce.spring.domain.order.repository;

import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;

public interface OrderItemQueryRepository {

    List<Product> selectPopularOrderItems();
}
