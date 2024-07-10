package com.hhplus.commerce.spring.api.service.product;

import com.hhplus.commerce.spring.model.entity.Product;
import com.hhplus.commerce.spring.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAllByOrderByIdDesc();
    }

    public List<Product> getPopulars() {
        return null;
    }
}
