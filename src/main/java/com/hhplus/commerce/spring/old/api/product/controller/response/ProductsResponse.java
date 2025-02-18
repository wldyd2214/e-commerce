package com.hhplus.commerce.spring.old.api.product.controller.response;

import com.hhplus.commerce.spring.old.api.product.controller.dto.ProductDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsResponse {

    @Schema(description = "상품 목록 정보")
    private List<ProductDTO> products;
}
