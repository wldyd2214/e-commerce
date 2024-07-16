package com.hhplus.commerce.spring.api.product.infrastructure.database;

import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

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
    public Optional<Product> findById(Long productId) {
        return jpaRepository.findById(productId);
    }
}
