package com.hhplus.commerce.spring.domain.order.repository;

import com.hhplus.commerce.spring.domain.product.model.Product;
import java.util.List;

public interface OrderItemQueryRepository {

    List<Product> selectPopularOrderItems();
}
