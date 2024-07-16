package com.hhplus.commerce.spring.api.product.controller.dto;

import com.hhplus.commerce.spring.api.product.controller.response.ProductsResponse;

import com.hhplus.commerce.spring.api.product.model.Product;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ProductsResponse toProductsResponse(List<Product> products) {
        return ProductsResponse.builder()
                               .products(
                                   products.stream()
                                           .map(ProductDTOMapper::toProductDTO)
                                           .collect(Collectors.toList())
                               )
                               .build();
    }

    public static ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                         .id(product.getId())
                         .name(product.getProductName())
                         .consumerPrice(product.getProductPrice())
                         .stockCount(product.getProductCount())
                         .build();
    }
}
