package com.hhplus.commerce.spring.domain.product.repository;

import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository {

    Optional<Product> findById(Long id);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByQuery(ProductQuery.List query);

    List<Product> findAllByIdIn(List<Long> productKeys);

    Optional<Product> findByIdWithPessimisticLock(Long productId);

    Long selectProductTotalCount(ProductQuery.List query);
}
