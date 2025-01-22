package com.hhplus.commerce.spring.domain.product.repository;

import com.hhplus.commerce.spring.old.api.product.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository {

    Optional<Product> findById(Long id);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIdIn(List<Long> productKeys);

    Optional<Product> findByIdWithPessimisticLock(Long productId);
}
