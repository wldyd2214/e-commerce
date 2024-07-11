package com.hhplus.commerce.spring.api.service;

import com.hhplus.commerce.spring.model.entity.Product;
import com.hhplus.commerce.spring.repository.OrderItemRepository;
import com.hhplus.commerce.spring.repository.ProductRepository;
import java.util.List;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Product> getProducts() {
        return productRepository.findAllByOrderByIdDesc();
    }

    public List<Product> getPopulars() {
        return orderItemRepository.selectPopularOrderItems();
    }
}
