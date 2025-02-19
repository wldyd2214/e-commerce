package com.hhplus.commerce.spring.infrastructure.product.repository;

import static com.hhplus.commerce.spring.domain.product.entity.QProduct.product;
import static org.springframework.util.StringUtils.hasText;

import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final ProductJpaRepository jpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Product> findAllByOrderByIdDesc() {
        return jpaRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Product> findAllByQuery(ProductQuery.List query) {
        return queryFactory.selectFrom(product)
                           .where(nameLike(query.getName()))
                           .orderBy(product.id.desc())
                           .offset((query.getPage() - 1) * query.getCount())
                           .limit(query.getCount())
                           .fetch();
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> productKeys) {
        return jpaRepository.findAllByIdIn(productKeys);
    }

    @Override
    public Optional<Product> findByIdWithPessimisticLock(Long productId) {
        return jpaRepository.findByIdWithPessimisticLock(productId);
    }

    @Override
    public Long selectProductTotalCount(ProductQuery.List query) {
        return queryFactory.selectFrom(product)
                           .where(nameLike(query.getName()))
                           .fetchCount();
    }

    private BooleanExpression nameLike(String name) {
        return hasText(name) ? product.name.contains(name) : null;
    }
}
