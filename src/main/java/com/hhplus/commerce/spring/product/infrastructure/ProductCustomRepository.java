package com.hhplus.commerce.spring.product.infrastructure;

import com.hhplus.commerce.spring.product.domain.model.Product;
import com.hhplus.commerce.spring.product.domain.repository.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepository implements ProductRepository {
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
