package com.hhplus.commerce.spring.domain.product.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDeductInfo {

    private Integer totalAmount;
    private List<ProductInfo> productInfos;
}
