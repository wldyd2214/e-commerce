package com.hhplus.commerce.spring.presentation.product;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPaged;
import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.domain.product.service.ProductService;
import com.hhplus.commerce.spring.presentation.product.dto.response.ProductResponse;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.product.dto.request.ProductListRequest;
import com.hhplus.commerce.spring.presentation.product.mapper.ProductRequestMapper;
import com.hhplus.commerce.spring.presentation.product.mapper.ProductResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "상품", description = "상품 관련 인터페이스를 제공합니다.")
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    private final ProductRequestMapper requestMapper;
    private final ProductResponseMapper responseMapper;

    @Operation(summary = "상품 목록 조회 API", description = "상품 목록 정보를 반환합니다.")
    @GetMapping(value = "")
    public ApiResponse<ProductResponse.PagedProduct> getPagedProducts(
        @ParameterObject @ModelAttribute ProductListRequest request) {

        // 1. 유효성 검증
        request.defaultValueSetting();

        // 2. 상품 목록 조회
        ProductQuery.Paged query = requestMapper.toProductQueryList(request);
        ProductInfoPaged productInfoPaged = productService.getPagedProducts(query);

        // 3. 응답 객체 변환
        ProductResponse.PagedProduct response = responseMapper.toPagedProduct(productInfoPaged);

        return ApiResponse.ok(response);
    }

    @Operation(
            summary = "상위 상품 목록 조회 API",
            description = "최근 3일간 가장 많이 판매된 상위 5개 상품 목록 정보를 반환합니다."
    )
    @GetMapping(value = "/popular")
    public ApiResponse<ProductResponse.PopularProduct> getPopulars() {

        // 1. 인기 상품 목록 조회
        List<ProductInfo> productInfoList = productService.getPopulars();

        // 2. 응답 객체 변환
        ProductResponse.PopularProduct response = responseMapper.toPopularProduct(productInfoList);

        return ApiResponse.ok(response);
    }
}
