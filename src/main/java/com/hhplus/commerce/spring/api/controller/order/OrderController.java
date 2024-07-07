package com.hhplus.commerce.spring.api.controller.order;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.controller.order.dto.OrderDTOMapper;
import com.hhplus.commerce.spring.api.controller.order.response.OrderPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @PostMapping(value = "/payment")
    public ApiResponse<OrderPaymentResponse> orderPayment() {
        return ApiResponse.ok(OrderDTOMapper.createDummyOrderPaymentResponse());
    }
}
