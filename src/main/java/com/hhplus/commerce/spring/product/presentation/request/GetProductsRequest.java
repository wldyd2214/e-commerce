package com.hhplus.commerce.spring.product.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetProductsRequest {

    @Schema(description = "상품명", example = "보스 QC")
    private String productName;

    // 페이징
    @Schema(title = "마지막 조회 결과의 ID (커서)", description = "마지막으로 조회된 상품의 ID. \n 첫 요청 시에는 null 또는 0 입력", example = "21")
    @NotNull
    private Long cursorId;

    @Schema(title = "한 번에 조회할 항목 수", description = "가져올 아이템 개수 \n - default 10", example = "10")
    @NotNull
    private Integer limit;

    public Integer getLimit() {
        // 기본값 설정
        if (limit == null || limit <= 0) {
            return 10; // 기본 10개
        }
        return limit;
    }
}
