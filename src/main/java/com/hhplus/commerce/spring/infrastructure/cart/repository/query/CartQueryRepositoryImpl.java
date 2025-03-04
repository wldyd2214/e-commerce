package com.hhplus.commerce.spring.infrastructure.cart.repository.query;

import static com.hhplus.commerce.spring.domain.cart.model.QCart.cart;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.repository.query.CartQueryRepository;
import com.hhplus.commerce.spring.infrastructure.cart.repository.CartJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CartQueryRepositoryImpl implements CartQueryRepository {

    private final CartJpaRepository jpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return Optional.ofNullable(queryFactory.selectFrom(cart)
                                               .where(cart.user.id.eq(userId)).fetchOne());
    }
}
