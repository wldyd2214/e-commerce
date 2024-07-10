package com.hhplus.commerce.spring.api.service.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderServiceRequest {

    private Long productId;
    private Integer orderCount;
}
