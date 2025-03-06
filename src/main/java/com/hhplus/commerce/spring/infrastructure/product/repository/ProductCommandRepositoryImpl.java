package com.hhplus.commerce.spring.infrastructure.product.repository;

import com.hhplus.commerce.spring.domain.product.model.Product;
import com.hhplus.commerce.spring.domain.product.repository.ProductCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductCommandRepositoryImpl implements ProductCommandRepository {

    private final ProductJpaRepository jpaRepository;

    @Override
    public List<Product> saveAll(List<Product> products) {
        return jpaRepository.saveAll(products);
    }
}
