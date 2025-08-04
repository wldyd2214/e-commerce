package com.hhplus.commerce.spring.old.domain.product.repository;

import com.hhplus.commerce.spring.old.domain.product.model.Product;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductQuery.Paged;
import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository {

    Optional<Product> findById(Long id);

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByQuery(Paged query);

    List<Product> findAllByIdIn(List<Long> productIds);

    List<Product> findAllByIdWithPessimisticLock(List<Long> productKeys);

    Long selectProductTotalCount(Paged query);
}
