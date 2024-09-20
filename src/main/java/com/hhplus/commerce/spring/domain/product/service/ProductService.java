package com.hhplus.commerce.spring.domain.product.service;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;

import java.util.List;

public interface ProductService {
    List<ProductInfo> getProductsByIds(List<Long> productIds);
}
