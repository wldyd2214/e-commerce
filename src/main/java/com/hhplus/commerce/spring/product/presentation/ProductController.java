package com.hhplus.commerce.spring.product.presentation;

import com.hhplus.commerce.spring.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품 관련 인터페이스를 제공합니다.")
@RequestMapping(value = "/api/v1/products")
public class ProductController {



    @Operation(summary = "상품 목록 조회 API", description = "상품 목록을 조회합니다.")
    @GetMapping("")
    public ApiResponse<Void> getProducts() {
        return ApiResponse.ok(null);
    }
}