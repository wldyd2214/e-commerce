package com.hhplus.commerce.spring.api.order.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("orderId")
    private Long orderId;
}
