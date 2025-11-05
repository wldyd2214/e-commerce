package com.hhplus.commerce.spring.product.infrastructure;

import com.hhplus.commerce.spring.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
