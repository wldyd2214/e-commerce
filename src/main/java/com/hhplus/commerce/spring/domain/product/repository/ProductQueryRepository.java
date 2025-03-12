package com.hhplus.commerce.spring.domain.product.repository;

import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository {

    Optional<Product> findById(Long id);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByQuery(ProductQuery.Paged query);

    List<Product> findAllByIdIn(List<Long> productIds);

    List<Product> findAllByIdWithPessimisticLock(List<Long> productKeys);

    Long selectProductTotalCount(ProductQuery.Paged query);
}
