package com.hhplus.commerce.spring.old.api.product.service;

import com.hhplus.commerce.spring.domain.product.entity.Product;
import com.hhplus.commerce.spring.domain.order.repository.OrderItemQueryRepository;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductOldService {
    private final ProductRepository productRepository;
    private final OrderItemQueryRepository orderItemRepository;

    public List<Product> getProducts() {
        return productRepository.findAllByOrderByIdDesc();
    }

    @Transactional(readOnly = true)
    @Cacheable("POPULAR_ITEM")
//    @Cacheable(value = "Populars", key = "5", cacheManager = "cacheManager")
    public List<Product> getPopulars() {
        return orderItemRepository.selectPopularOrderItems();
    }
}
