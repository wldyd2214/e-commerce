package com.hhplus.commerce.spring.old.domain.order.repository;

import com.hhplus.commerce.spring.old.domain.product.model.Product;
import java.util.List;

public interface OrderItemQueryRepository {

    List<Product> selectPopularOrderItems();
}
