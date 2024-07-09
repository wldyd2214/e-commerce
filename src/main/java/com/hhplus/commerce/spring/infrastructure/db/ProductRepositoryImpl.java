package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.Product;
import com.hhplus.commerce.spring.repository.ProductRepository;
import java.util.List;
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
}
