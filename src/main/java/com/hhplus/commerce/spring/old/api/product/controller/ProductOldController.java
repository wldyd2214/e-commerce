package com.hhplus.commerce.spring.old.api.product.controller;

import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.product.dto.response.ProductsResponse;
import com.hhplus.commerce.spring.old.api.product.service.ProductOldService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/products/v1")
public class ProductOldController {

    private final ProductOldService productOldService;

    @Operation(
        summary = "상품 목록 조회 API",
        description = "상품 목록 정보를 반환합니다."
    )
    @GetMapping(value = "")
    public ApiResponse<ProductsResponse> getProducts() {
//        ProductDTOMapper.toProductsResponse(productOldService.getProducts());
        return ApiResponse.ok(null);
    }

    @Operation(
        summary = "상위 상품 목록 조회 API",
        description = "최근 3일간 가장 많이 판매된 상위 5개 상품 목록 정보를 반환합니다."
    )
    @GetMapping(value = "/popular")
    public ApiResponse<ProductsResponse> getPopulars() {
//        ProductDTOMapper.toProductsResponse(productOldService.getPopulars());
        return ApiResponse.ok(null);
    }

}
