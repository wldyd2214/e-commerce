package com.hhplus.commerce.spring.old.domain.product.service;

import com.hhplus.commerce.spring.old.domain.order.repository.OrderItemQueryRepository;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductDeductInfo;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfoPaged;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.old.domain.product.dto.request.ProductCommand;
import com.hhplus.commerce.spring.old.domain.product.dto.request.ProductCommand.DeductProduct;
import com.hhplus.commerce.spring.old.domain.product.mapper.ProductInfoMapper;
import com.hhplus.commerce.spring.old.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.old.domain.product.model.Product;
import com.hhplus.commerce.spring.old.infrastructure.product.repository.ProductJpaRepository;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductQuery.Paged;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductQueryRepository productQueryRepository;
    private final OrderItemQueryRepository orderItemRepository;

    private final ProductInfoMapper productInfoMapper;

    private final ProductJpaRepository jpaRepository;

    @Transactional(readOnly = true)
    public ProductInfoPaged getPagedProducts(Paged query) {

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

        // 1. 상위 상품 목록 조회
        List<Product> populars = orderItemRepository.selectPopularOrderItems();

        // 2. 응답 객체 변환
        return productInfoMapper.toProductInfoList(populars);
    }

    @Transactional
    public ProductDeductInfo deductProductQuantities(ProductCommand.Deduct command) {

        // 1. 주문된 상품 조회
        List<Long> productIds =
            command.getProducts().stream().map(DeductProduct::getId).toList();

        Map<Long, DeductProduct> deductProductMap = createDeductProductMap(command.getProducts());
        Map<Long, Product> productMap = createProductMap(productIds);

        // 2. 상품 재고 차감
        int totalAmount = 0;

        for (Long productId : productMap.keySet()) {
            Product product = productMap.get(productId);
            int deductCount = deductProductMap.get(productId).getCount();
            product.deductQuantity(deductCount);

            totalAmount += (product.getPrice() * deductCount);
        }

        return productInfoMapper.toProductDeductInfo(totalAmount, productMap);
    }

    @Transactional(readOnly = true)
    public List<ProductInfo> getProducts(List<Long> productIds) {

        List<Product> products = productQueryRepository.findAllByIdIn(productIds);

        return productInfoMapper.toProductInfoList(products);
    }

    public void validateAvailability(ProductQuery.Validation query) {



        throw new RuntimeException();
    }

    private Map<Long, DeductProduct> createDeductProductMap(List<DeductProduct> products) {
        return products.stream()
            .collect(Collectors.toMap(product -> product.getId(), p -> p));
    }

    /**
     * 재고 감소 -> 동시성 고민
     * optimistic lock / pessimistic lock / ...
     * 동시의 여러개의 주문이 들어오는 경우
     * 재고 10개 <- 1번 주문 재고 9개 구매, 2번 주문 재고 2개 구매인 경우
     */
    private Map<Long, Product> createProductMap(List<Long> productIds) {

        List<Product> products =
            productQueryRepository.findAllByIdWithPessimisticLock(productIds);

        return products.stream()
            .collect(Collectors.toMap(Product::getId, p -> p));
    }
}
