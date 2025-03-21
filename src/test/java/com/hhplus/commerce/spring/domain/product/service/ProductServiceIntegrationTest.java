package com.hhplus.commerce.spring.domain.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPaged;
import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.model.Product;
import com.hhplus.commerce.spring.domain.order.repository.OrderItemQueryRepository;
import com.hhplus.commerce.spring.domain.product.repository.ProductCommandRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ProductServiceIntegrationTest {

    @Autowired
    ProductCommandRepository productCommandRepository;

    @Autowired
    OrderItemQueryRepository orderItemRepository;

    @Autowired
    ProductService productService;

    String productNameFormat = "테스트 %d번 상품";
    String productDescFormat = "테스트 %d번 상품 입니다.";
    Integer price = 100000;
    Integer count = 100;
    int minSize = 1;
    int maxSize = 16;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 준비
        List<Product> products = createProducts();
        productCommandRepository.saveAll(products);
    }

    private List<Product> createProducts() {

        List<Product> saveProducts = new ArrayList<>();

        for (int i = minSize; i < maxSize; i++) {
            saveProducts.add(
                Product.createProduct(
                    String.format(productNameFormat, i),
                    String.format(productDescFormat, i),
                    price,
                    count
                )
            );
        }

        return saveProducts;
    }

    @DisplayName("상품 목록 조회 - 성공")
    @Test
    void getPagedProducts() {

        int pageCount = 10;

        ProductQuery.Paged query = new ProductQuery.Paged(pageCount, 1, "테스트");

        // when // then
        ProductInfoPaged result = productService.getPagedProducts(query);

        assertThat(result).isNotNull();
        assertThat(result.getProductInfoList()).hasSize(pageCount)
                                               .extracting("name", "desc", "price", "stockCount")
                                               .contains(
                                                   tuple(String.format(productNameFormat, 15), String.format(productDescFormat, 15), price, count)
                                               );
    }
    @DisplayName("상위 상품 목록 조회 - 성공")
    @Test
    void getPopulars() {
        // given
        // 1. 상위 상품 목록에 조회될 수 있도록 데이터 준비 작업 진행

        // when
        // then
    }
}