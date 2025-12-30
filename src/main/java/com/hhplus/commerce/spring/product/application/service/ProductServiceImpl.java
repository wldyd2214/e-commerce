package com.hhplus.commerce.spring.product.application.service;

import com.hhplus.commerce.spring.product.application.dto.ProductSummaryInfo;
import com.hhplus.commerce.spring.product.domain.dto.query.ProductQuery;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Override
    public Page<ProductSummaryInfo> getProducts(ProductQuery.SummeryList query) {
        return new PageImpl<>(Collections.emptyList(), PageRequest.of(query.getPage(), query.getSize()), 0);
    }
}
