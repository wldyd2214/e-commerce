package com.hhplus.commerce.spring.presentation.order.controller;

import com.hhplus.commerce.spring.application.order.OrderFacade;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.order.mapper.OrderRequestMapper;
import com.hhplus.commerce.spring.presentation.order.dto.request.OrderRequestDTO;
import com.hhplus.commerce.spring.presentation.order.dto.response.OrderResponseDTO;
import com.hhplus.commerce.spring.presentation.order.mapper.OrderResponseMapper;
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
    private final OrderFacade orderFacade;

    @Operation(
        summary = "상품 주문 API",
        description = "상품을 주문합니다."
    )
    @PostMapping(value = "")
    public ApiResponse<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO request) {
        return ApiResponse.ok(
                OrderResponseMapper.toOrder(
                        orderFacade.order(OrderRequestMapper.toOrder(request))
                )
        );
    }
}
