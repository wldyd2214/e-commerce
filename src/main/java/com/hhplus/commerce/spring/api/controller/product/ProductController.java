package com.hhplus.commerce.spring.api.controller.product;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTOMapper;
import com.hhplus.commerce.spring.api.controller.product.response.ProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @GetMapping(value = "")
    public ApiResponse<ProductsResponse> getProducts() {
        return ApiResponse.ok(ProductDTOMapper.createDummyProductsResponse());
    }

    @GetMapping(value = "/popular")
    public ApiResponse<ProductsResponse> getPopulars() {
        return ApiResponse.ok(ProductDTOMapper.createDummyProductsResponse());
    }

}
