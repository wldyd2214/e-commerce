package com.hhplus.commerce.spring.domain.product.repository;

import com.hhplus.commerce.spring.domain.product.model.Product;
import java.util.List;

public interface ProductCommandRepository {

    List<Product> saveAll(List<Product> products);
}
