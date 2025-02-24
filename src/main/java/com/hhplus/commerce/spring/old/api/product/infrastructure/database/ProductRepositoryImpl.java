package com.hhplus.commerce.spring.old.api.product.infrastructure.database;

import com.hhplus.commerce.spring.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductQueryRepository jpaRepository;

    @Override
    public List<Product> findAllByOrderByIdDesc() {
        return jpaRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> productKeys) {
        return jpaRepository.findAllByIdIn(productKeys);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return jpaRepository.findById(productId);
    }
}
