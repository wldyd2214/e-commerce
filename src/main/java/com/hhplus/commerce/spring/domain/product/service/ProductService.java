package com.hhplus.commerce.spring.domain.product.service;

import com.hhplus.commerce.spring.domain.order.repository.OrderItemRepository;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.old.api.product.model.Product;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductQueryRepository productQueryRepository;
    private final OrderItemRepository orderItemRepository;

    public List<ProductInfo> getProductsByIds(List<Long> productIds) {
        productQueryRepository.findAllByIdIn(productIds);
        return null;
    }

    public List<Product> getProducts() {
        return productQueryRepository.findAllByOrderByIdDesc();
    }

    @Transactional(readOnly = true)
    @Cacheable("POPULAR_ITEM")
//    @Cacheable(value = "Populars", key = "5", cacheManager = "cacheManager")
    public List<Product> getPopulars() {
        return orderItemRepository.selectPopularOrderItems();
    }
}
