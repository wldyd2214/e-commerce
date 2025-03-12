package com.hhplus.commerce.spring.domain.product.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfoPaged {

    private Integer totalCount;
    private Integer currentPage;
    private List<ProductInfo> productInfoList;
}
