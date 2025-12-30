package com.hhplus.commerce.spring.product.infrastructure.persistence.spec;

import com.hhplus.commerce.spring.product.domain.entity.ProductView;
import com.hhplus.commerce.spring.product.domain.entity.ProductView_;
import com.hhplus.commerce.spring.product.domain.type.ProductStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class ProductViewSpecs {

    public static Specification<ProductView> productId(Long productId) {
        return (Root<ProductView> root, CriteriaQuery<?> query,
        CriteriaBuilder cb) -> cb.equal(root.get(ProductView_.id), productId);
    }

    public static Specification<ProductView> isActive() {
        return (root, query, cb) ->  {
            Predicate isActive = cb.equal(root.get(ProductView_.isActive), true);
            return cb.and(isActive);
        };
    }

    public static Specification<ProductView> isSelling() {
        return (root, query, cb) -> {
//            LocalDateTime now = LocalDateTime.now();

            // 조건 1: 상품의 판매 시작일이 '조회 종료일'보다 이전이거나 같아야 한다.
            // (root.get(ProductView_.sellStartDate) <= to)
//            Predicate toCondition = cb.lessThanOrEqualTo(root.get(ProductView_.sellStartedAt), now);

            // 조건 2: 상품의 판매 종료일이 '조회 시작일'보다 이후이거나 같아야 한다.
            // (root.get(ProductView_.sellEndDate) >= from)
//            Predicate fromCondition = cb.greaterThanOrEqualTo(root.get(ProductView_.sellStopedAt), now);

            Predicate status = cb.equal(root.get(ProductView_.status), ProductStatus.ACTIVE);
            Predicate isActive = cb.equal(root.get(ProductView_.isActive), true);
            Predicate isDeleted = cb.equal(root.get(ProductView_.isDeleted), false);
            return cb.and(status, isActive, isDeleted);
        };
    }
}
