package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.old.application.order.dto.response.OrderItemFacadeDTO;
import com.hhplus.commerce.spring.old.application.order.mapper.OrderFacadeResponseMapper;
import com.hhplus.commerce.spring.old.domain.order.dto.OrderInfo;
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
public class OrderFacadeResponseMapperImpl implements OrderFacadeResponseMapper {

    @Override
    public OrderFacadeResponse.Create toCreate(OrderInfo orderInfo) {
        if ( orderInfo == null ) {
            return null;
        }

        List<OrderItemFacadeDTO> orders = null;
        Long id = null;
        Long userId = null;

        orders = orderItemInfoListToOrderItemFacadeDTOList( orderInfo.getOrderItems() );
        id = orderInfo.getId();
        userId = orderInfo.getUserId();

        OrderFacadeResponse.Create create = new OrderFacadeResponse.Create( id, userId, orders );

        return create;
    }

    protected OrderItemFacadeDTO orderItemInfoToOrderItemFacadeDTO(OrderInfo.OrderItemInfo orderItemInfo) {
        if ( orderItemInfo == null ) {
            return null;
        }

        OrderItemFacadeDTO.OrderItemFacadeDTOBuilder orderItemFacadeDTO = OrderItemFacadeDTO.builder();

        orderItemFacadeDTO.id( orderItemInfo.getId() );
        orderItemFacadeDTO.productName( orderItemInfo.getProductName() );
        orderItemFacadeDTO.productPrice( orderItemInfo.getProductPrice() );
        orderItemFacadeDTO.orderCount( orderItemInfo.getOrderCount() );

        return orderItemFacadeDTO.build();
    }

    protected List<OrderItemFacadeDTO> orderItemInfoListToOrderItemFacadeDTOList(List<OrderInfo.OrderItemInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemFacadeDTO> list1 = new ArrayList<OrderItemFacadeDTO>( list.size() );
        for ( OrderInfo.OrderItemInfo orderItemInfo : list ) {
            list1.add( orderItemInfoToOrderItemFacadeDTO( orderItemInfo ) );
        }

        return list1;
    }
}
