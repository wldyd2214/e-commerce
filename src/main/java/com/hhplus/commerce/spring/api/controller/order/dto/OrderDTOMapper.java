package com.hhplus.commerce.spring.api.controller.order.dto;

import com.hhplus.commerce.spring.api.controller.order.request.OrderPaymentRequest;
import com.hhplus.commerce.spring.api.controller.order.response.OrderPaymentResponse;
import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;
import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTOMapper;

import com.hhplus.commerce.spring.api.service.order.request.OrderPaymentServiceRequest;
import com.hhplus.commerce.spring.api.service.order.request.OrderServiceRequest;
import com.hhplus.commerce.spring.model.entity.OrderItem;
import com.hhplus.commerce.spring.model.entity.Product;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper {

    public static OrderPaymentServiceRequest toOrderPaymentServiceRequest(OrderPaymentRequest request) {
        return OrderPaymentServiceRequest.builder()
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

    public static OrderPaymentResponse toOrderPaymentResponse(List<OrderItem> orderPayment) {
        return OrderPaymentResponse.builder()
                                   .orderItems(
                                       orderPayment.stream()
                                                   .map(OrderDTOMapper::toOrderDTO)
                                                   .collect(Collectors.toList()))
                                   .build();
    }

    public static OrderDTO toOrderDTO(OrderItem orderItem) {
        return OrderDTO.builder()
                       .orderId(orderItem.getOrder().getId())
                       .sellPrice(orderItem.getOrderProductPrice())
                       .orderCount(orderItem.getOrderProductCount())
                       .product(toProductDTO(orderItem.getProduct()))
                       .build();
    }

    public static ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                         .id(product.getId())
                         .name(product.getProductName())
                         .consumerPrice(product.getProductPrice())
                         .stockCount(product.getProductCount())
                         .build();
    }
}
