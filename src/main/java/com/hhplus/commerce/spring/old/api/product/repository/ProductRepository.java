package com.hhplus.commerce.spring.old.api.product.repository;

import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIdIn(List<Long> productKeys);

    Optional<Product> findById(Long productId);

    Optional<Product> findByIdWithPessimisticLock(Long productId);
}
