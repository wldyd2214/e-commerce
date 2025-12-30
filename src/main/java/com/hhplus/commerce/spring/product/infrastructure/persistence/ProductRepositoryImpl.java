package com.hhplus.commerce.spring.product.infrastructure.persistence;

import com.hhplus.commerce.spring.product.domain.entity.Product;
import com.hhplus.commerce.spring.product.domain.repository.ProductRepository;
import com.hhplus.commerce.spring.product.infrastructure.persistence.jpa.ProductJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    Page<Product> getProducts() {
//        return jpaQueryFactory.selectFrom(product).fetchResults().getResults();
        return null;
    }

    List<Product> findAll(){
        return productJpaRepository.findAll();
    }
}
