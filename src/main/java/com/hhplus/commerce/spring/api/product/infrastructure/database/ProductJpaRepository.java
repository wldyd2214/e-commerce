package com.hhplus.commerce.spring.api.product.infrastructure.database;

import com.hhplus.commerce.spring.api.product.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIdIn(List<Long> productKeys);
}
