package com.hhplus.commerce.spring.domain.product.service;

import com.hhplus.commerce.spring.domain.order.repository.OrderItemRepository;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoList;
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

    public List<ProductInfo> getProductsByIds(List<Long> productIds) {
        productQueryRepository.findAllByIdIn(productIds);
        return null;
    }

    public ProductInfoList getProducts(ProductQuery.List query) {

        List<Product> products = productQueryRepository.findAllByQuery(query);

        Long totalCount = productQueryRepository.selectProductTotalCount(query);

        List<ProductInfo> productInfoList =
            ProductInfoMapper.INSTANCE.toProductInfoList(products);

        return ProductInfoList.builder()
                              .totalCount(totalCount.intValue())
                              .currentPage(query.getPage())
                              .productInfoList(productInfoList)
                              .build();
    }

    @Transactional(readOnly = true)
    @Cacheable("POPULAR_ITEM")
//    @Cacheable(value = "Populars", key = "5", cacheManager = "cacheManager")
    public List<Product> getPopulars() {
        return orderItemRepository.selectPopularOrderItems();
    }
}
