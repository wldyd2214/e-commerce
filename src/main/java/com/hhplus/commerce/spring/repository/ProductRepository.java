package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAllByOrderByIdDesc();

    Optional<Product> findById(Long productId);
}
