package com.hhplus.commerce.spring.domain.product.service.impl;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.service.ProductService;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductInfo> getProductsByIds(List<Long> productIds) {


        productRepository.findAllByIdIn(productIds);

        return null;
    }
}
