package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.Product;
import java.util.List;

public interface ProductRepository {

    List<Product> findAllByOrderByIdDesc();
}
