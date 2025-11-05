package com.hhplus.commerce.spring.product.domain.service;

import com.hhplus.commerce.spring.product.domain.dto.ProductSummaryInfo;
import com.hhplus.commerce.spring.product.domain.dto.query.ProductQuery;
import org.springframework.data.domain.Page;

public interface ProductService {

    // 상품 목록 조회
    Page<ProductSummaryInfo> getProducts(ProductQuery.SummeryList query);
}
