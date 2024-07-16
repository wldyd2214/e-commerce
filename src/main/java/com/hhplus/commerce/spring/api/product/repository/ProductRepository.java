package com.hhplus.commerce.spring.api.product.repository;

import com.hhplus.commerce.spring.api.product.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIdIn(List<Long> productKeys);

    Optional<Product> findById(Long productId);
}
