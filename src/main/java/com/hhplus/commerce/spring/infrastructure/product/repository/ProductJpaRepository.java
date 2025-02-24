package com.hhplus.commerce.spring.infrastructure.product.repository;

import com.hhplus.commerce.spring.domain.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIdIn(List<Long> productKeys);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id IN :productIds")
    List<Product> findAllByIdWithPessimisticLock(List<Long> productIds);
}
