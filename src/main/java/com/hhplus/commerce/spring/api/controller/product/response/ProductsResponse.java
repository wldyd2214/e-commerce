package com.hhplus.commerce.spring.api.controller.product.response;

import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;
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

    private List<ProductDTO> products;
}
