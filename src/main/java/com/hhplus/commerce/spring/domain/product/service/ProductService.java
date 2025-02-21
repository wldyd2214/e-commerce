package com.hhplus.commerce.spring.domain.product.service;

import com.hhplus.commerce.spring.domain.order.repository.OrderItemRepository;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPage;
import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.mapper.ProductInfoMapper;
import com.hhplus.commerce.spring.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.domain.product.entity.Product;
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

    private final ProductInfoMapper productInfoMapper;

    public ProductInfoPage getProducts(ProductQuery.List query) {

        // 1. 상품 목록 정보 조회
        List<Product> products = productQueryRepository.findAllByQuery(query);

        // 2. 전체 카운트 조회
        Long totalCount = productQueryRepository.selectProductTotalCount(query);

        // 3. 응답 객체 변환
        List<ProductInfo> productInfoList = productInfoMapper.toProductInfoList(products);

        return productInfoMapper.toProductInfoPage(totalCount.intValue(), query.getPage(), productInfoList);
    }

    @Transactional(readOnly = true)
    @Cacheable("POPULAR_ITEM")
//    @Cacheable(value = "Populars", key = "5", cacheManager = "cacheManager")
    public List<ProductInfo> getPopulars() {

        List<Product> populars = orderItemRepository.selectPopularOrderItems();

        return productInfoMapper.toProductInfoList(populars);
    }
}
