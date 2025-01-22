package com.hhplus.commerce.spring.infrastructure.product.repository;

import com.hhplus.commerce.spring.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.old.api.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final ProductJpaRepository jpaRepository;

    @Override
    public List<Product> findAllByOrderByIdDesc() {
        return jpaRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> productKeys) {
        return jpaRepository.findAllByIdIn(productKeys);
    }

    @Override
    public Optional<Product> findByIdWithPessimisticLock(Long productId) {
        return jpaRepository.findByIdWithPessimisticLock(productId);
    }
}
