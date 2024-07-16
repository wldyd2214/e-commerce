package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findAllByIdIn(List<Long> productKeys);
}
