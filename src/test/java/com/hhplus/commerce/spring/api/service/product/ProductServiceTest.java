package com.hhplus.commerce.spring.api.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.api.service.product.ProductService;
import com.hhplus.commerce.spring.model.entity.Product;
import com.hhplus.commerce.spring.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @DisplayName("상품 목록 조회를 성공한다.")
    @Test
    void getProducts() {
        int productLength = 5;

        List<Product> products = createProducts(productLength);

        // given
        given(productRepository.findAllByOrderByIdDesc())
            .willReturn(products);

        // when // then
        List<Product> resProducts = productService.getProducts();

        assertThat(resProducts).isNotNull();
        assertThat(resProducts).hasSize(productLength)
                            .extracting("id", "productName", "productDesc", "productPrice", "productCount")
                            .containsExactlyInAnyOrder(
                                tuple(1L, "1번 제품 이름", "1번 제품 내용", 100000, 50),
                                tuple(2L, "2번 제품 이름", "2번 제품 내용", 100000, 50),
                                tuple(3L, "3번 제품 이름", "3번 제품 내용", 100000, 50),
                                tuple(4L, "4번 제품 이름", "4번 제품 내용", 100000, 50),
                                tuple(5L, "5번 제품 이름", "5번 제품 내용", 100000, 50)
                            );
    }

    private List<Product> createProducts(int num) {

        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= num; i++) {
            products.add(createProduct(i));
        }

        return products;
    }

    private Product createProduct(long id) {
        return Product.builder()
                      .id(id)
                      .productName(String.format("%d번 제품 이름", id))
                      .productDesc(String.format("%d번 제품 내용", id))
                      .productPrice(100000)
                      .productCount(50)
                      .build();
    }
}