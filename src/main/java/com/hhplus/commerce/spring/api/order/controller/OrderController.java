package com.hhplus.commerce.spring.api.order.controller;

import com.hhplus.commerce.spring.api.common.response.ApiResponse;
import com.hhplus.commerce.spring.api.order.controller.dto.OrderDTOMapper;
import com.hhplus.commerce.spring.api.order.controller.request.CreateOrderRequest;
import com.hhplus.commerce.spring.api.order.controller.response.CreateOrderResponse;
import com.hhplus.commerce.spring.api.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(
        summary = "상품 주문 API",
        description = "상품을 주문합니다."
    )
    @PostMapping(value = "")
    public ApiResponse<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return ApiResponse.ok(OrderDTOMapper.toOrderPaymentResponse(
            orderService.createOrder(OrderDTOMapper.toCreateOrderServiceRequest(request))));
    }
}
