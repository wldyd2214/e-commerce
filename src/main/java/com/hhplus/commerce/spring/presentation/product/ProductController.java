package com.hhplus.commerce.spring.presentation.product;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoList;
import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.service.ProductService;
import com.hhplus.commerce.spring.old.api.product.controller.dto.ProductDTOMapper;
import com.hhplus.commerce.spring.old.api.product.controller.response.ProductsResponse;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.product.dto.request.ProductListRequest;
import com.hhplus.commerce.spring.presentation.product.dto.response.ProductListResponse;
import com.hhplus.commerce.spring.presentation.product.mapper.ProductRequestMapper;
import com.hhplus.commerce.spring.presentation.product.mapper.ProductResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v2/products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "상품 목록 조회 API",
            description = "상품 목록 정보를 반환합니다."
    )
    @GetMapping(value = "")
    public ApiResponse<ProductListResponse> getProducts(
        @ParameterObject @ModelAttribute ProductListRequest request) {

        request.defaultValueSetting();

        ProductQuery.List query = ProductRequestMapper.INSTANCE.toProductQueryList(request);

        ProductInfoList productInfoList = productService.getProducts(query);

        ProductListResponse response =
            ProductResponseMapper.INSTANCE.toProductResponse(productInfoList);

        return ApiResponse.ok(response);
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
