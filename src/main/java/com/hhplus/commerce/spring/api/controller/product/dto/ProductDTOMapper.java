package com.hhplus.commerce.spring.api.controller.product.dto;

import com.hhplus.commerce.spring.api.controller.product.response.ProductsResponse;

import java.util.List;

public class ProductDTOMapper {

    public static ProductsResponse createDummyProductsResponse() {
        return ProductsResponse.builder()
                               .products(
                                       List.of(
                                               createProductDTO(1L, "아더에러 모자", 100000, 10),
                                               createProductDTO(2L, "아더에러 바지", 260000, 50),
                                               createProductDTO(3L, "아더에러 티셔츠", 170000, 30),
                                               createProductDTO(4L, "아더에러 신발", 160000, 30),
                                               createProductDTO(5L, "아더에러 반지", 90000, 20)
                                       )
                               )
                               .build();
    }

    public static ProductDTO createProductDTO(Long id, String name, int price, int count) {
        return ProductDTO.builder()
                         .id(id)
                         .name(name)
                         .consumerPrice(price)
                         .stockCount(count)
                         .build();
    }
}
