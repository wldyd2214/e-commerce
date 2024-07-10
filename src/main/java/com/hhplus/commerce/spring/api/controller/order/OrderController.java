package com.hhplus.commerce.spring.api.controller.order;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.controller.order.dto.OrderDTOMapper;
import com.hhplus.commerce.spring.api.controller.order.request.OrderPaymentRequest;
import com.hhplus.commerce.spring.api.controller.order.response.OrderPaymentResponse;
import com.hhplus.commerce.spring.api.service.order.OrderService;
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
        summary = "상품 주문/결제 API",
        description = "상품을 주문/결제 합니다."
    )
    @PostMapping(value = "/payment")
    public ApiResponse<OrderPaymentResponse> orderPayment(@RequestBody @Valid OrderPaymentRequest request) {
        return ApiResponse.ok(OrderDTOMapper.toOrderPaymentResponse(
            orderService.orderPayment(OrderDTOMapper.toOrderPaymentServiceRequest(request))));
    }
}
