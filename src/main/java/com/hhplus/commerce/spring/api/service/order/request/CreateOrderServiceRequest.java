package com.hhplus.commerce.spring.api.service.order.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderServiceRequest {

    private Long userId;
    private List<OrderServiceRequest> orders;
}
