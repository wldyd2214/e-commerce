package com.hhplus.commerce.spring.api.controller.order.dto;

import com.hhplus.commerce.spring.api.controller.order.request.CreateOrderRequest;
import com.hhplus.commerce.spring.api.controller.order.response.CreateOrderResponse;
import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;

import com.hhplus.commerce.spring.api.service.order.request.CreateOrderServiceRequest;
import com.hhplus.commerce.spring.api.service.order.request.OrderServiceRequest;
import com.hhplus.commerce.spring.model.entity.Order;
import com.hhplus.commerce.spring.model.entity.OrderItem;
import com.hhplus.commerce.spring.model.entity.Product;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper {

    public static CreateOrderServiceRequest toCreateOrderServiceRequest(CreateOrderRequest request) {
        return CreateOrderServiceRequest.builder()
                                        .userId(request.getUserId())
                                        .orders(request.getOrderItems()
                                                        .stream()
                                                        .map(OrderDTOMapper::toOrderServiceRequest)
                                                        .collect(Collectors.toList()))
                                        .build();
    }

    public static OrderServiceRequest toOrderServiceRequest(OrderPaymentDTO dto) {
        return OrderServiceRequest.builder()
                                  .productId(dto.getProductId())
                                  .orderCount(dto.getOrderCount())
                                  .build();
    }

    public static CreateOrderResponse toOrderPaymentResponse(Order order) {
        return CreateOrderResponse.builder()
                                  .order(toOrderDTO(order))
                                  .build();
    }

    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                       .id(order.getId())
                       .userId(order.getUser().getId())
                       .orderItems(order.getOrderItem()
                                        .stream()
                                        .map(OrderDTOMapper::toOrderItemDTO)
                                        .collect(Collectors.toList()))
                       .build();
    }

    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                           .id(orderItem.getId())
                           .orderProductName(orderItem.getOrderProductName())
                           .orderProductPrice(orderItem.getOrderProductPrice())
                           .orderProductCount(orderItem.getOrderProductCount())
                           .build();
    }
}
