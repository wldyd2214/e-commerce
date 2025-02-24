package com.hhplus.commerce.spring.presentation.order;

import com.hhplus.commerce.spring.application.order.OrderFacade;
import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.order.mapper.OrderRequestMapper;
import com.hhplus.commerce.spring.presentation.order.dto.request.OrderRequest;
import com.hhplus.commerce.spring.presentation.order.dto.response.OrderResponse;
import com.hhplus.commerce.spring.presentation.order.mapper.OrderResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "주문", description = "주문 관련 인터페이스를 제공합니다.")
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    private final OrderRequestMapper requestMapper;
    private final OrderResponseMapper responseMapper;

    @Operation(summary = "상품 주문 API", description = "상품을 주문합니다.")
    @PostMapping(value = "")
    public ApiResponse<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {

        // 1. 파사드 레이어로 요청하기 위한 요청 객체 변환
        OrderFacadeRequest.Create createRequest = requestMapper.toOrderCreate(request);

        // 2. 파사드 레이어 주문 요청
        OrderFacadeResponse.Create facadeResponse = orderFacade.orderCreate(createRequest);

        // 3. 응답 규격 변환
        OrderResponse response = responseMapper.toOrderCreate(facadeResponse);

        return ApiResponse.ok(response);
    }
}
