package com.hhplus.commerce.spring.old.api.order.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderEvent {

    private Long userId;
    private Long orderId;
}
