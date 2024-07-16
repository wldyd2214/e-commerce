package com.hhplus.commerce.spring.api.product.controller;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.product.controller.dto.ProductDTOMapper;
import com.hhplus.commerce.spring.api.product.controller.response.ProductsResponse;
import com.hhplus.commerce.spring.api.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @Operation(
        summary = "상품 목록 조회 API",
        description = "상품 목록 정보를 반환합니다."
    )
    @GetMapping(value = "")
    public ApiResponse<ProductsResponse> getProducts() {
        return ApiResponse.ok(ProductDTOMapper.toProductsResponse(productService.getProducts()));
    }

    @Operation(
        summary = "상위 상품 목록 조회 API",
        description = "최근 3일간 가장 많이 판매된 상위 5개 상품 목록 정보를 반환합니다."
    )
    @GetMapping(value = "/popular")
    public ApiResponse<ProductsResponse> getPopulars() {
        return ApiResponse.ok(ProductDTOMapper.toProductsResponse(productService.getPopulars()));
    }

}
