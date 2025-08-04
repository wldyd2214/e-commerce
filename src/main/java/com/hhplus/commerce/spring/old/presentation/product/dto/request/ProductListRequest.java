package com.hhplus.commerce.spring.old.presentation.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductListRequest {

    @Schema(hidden = true)
    private final Integer DEFAULT_PAGE_COUNT = 10;

    @Schema(description = "요청 개수")
    private Integer count;

    @Schema(description = "요청 페이지")
    private Integer page;

    @Schema(description = "상품명")
    private String name;

    public void defaultValueSetting() {
        if (Objects.isNull(count)) this.count = DEFAULT_PAGE_COUNT;
        if (Objects.isNull(page) || page == 0) this.page = 1;
    }
}
