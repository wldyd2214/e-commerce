package com.hhplus.commerce.spring.presentation.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.old.application.order.dto.response.OrderItemFacadeDTO;
import com.hhplus.commerce.spring.old.presentation.order.dto.OrderItemDTO;
import com.hhplus.commerce.spring.old.presentation.order.dto.response.OrderResponse;
import com.hhplus.commerce.spring.old.presentation.order.mapper.OrderResponseMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class OrderResponseMapperImpl implements OrderResponseMapper {

    @Override
    public OrderResponse toOrderCreate(OrderFacadeResponse.Create create) {
        if ( create == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.orderItems( orderItemFacadeDTOListToOrderItemDTOList( create.getOrders() ) );
        orderResponse.id( create.getId() );
        orderResponse.userId( create.getUserId() );

        return orderResponse.build();
    }

    protected OrderItemDTO orderItemFacadeDTOToOrderItemDTO(OrderItemFacadeDTO orderItemFacadeDTO) {
        if ( orderItemFacadeDTO == null ) {
            return null;
        }

        OrderItemDTO.OrderItemDTOBuilder orderItemDTO = OrderItemDTO.builder();

        orderItemDTO.id( orderItemFacadeDTO.getId() );
        orderItemDTO.productName( orderItemFacadeDTO.getProductName() );
        orderItemDTO.productPrice( orderItemFacadeDTO.getProductPrice() );
        orderItemDTO.orderCount( orderItemFacadeDTO.getOrderCount() );

        return orderItemDTO.build();
    }

    protected List<OrderItemDTO> orderItemFacadeDTOListToOrderItemDTOList(List<OrderItemFacadeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDTO> list1 = new ArrayList<OrderItemDTO>( list.size() );
        for ( OrderItemFacadeDTO orderItemFacadeDTO : list ) {
            list1.add( orderItemFacadeDTOToOrderItemDTO( orderItemFacadeDTO ) );
        }

        return list1;
    }
}
