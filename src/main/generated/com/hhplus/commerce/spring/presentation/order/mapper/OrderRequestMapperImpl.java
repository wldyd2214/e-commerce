package com.hhplus.commerce.spring.presentation.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.OrderFacadeDTO;
import com.hhplus.commerce.spring.old.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.old.presentation.order.dto.request.OrderItemRequest;
import com.hhplus.commerce.spring.old.presentation.order.dto.request.OrderRequest;
import com.hhplus.commerce.spring.old.presentation.order.mapper.OrderRequestMapper;
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
public class OrderRequestMapperImpl implements OrderRequestMapper {

    @Override
    public OrderFacadeRequest.Create toOrderCreate(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        List<OrderFacadeDTO> orders = null;
        Long userId = null;

        orders = orderItemRequestListToOrderFacadeDTOList( request.getOrderItems() );
        userId = request.getUserId();

        OrderFacadeRequest.Create create = new OrderFacadeRequest.Create( userId, orders );

        return create;
    }

    protected OrderFacadeDTO orderItemRequestToOrderFacadeDTO(OrderItemRequest orderItemRequest) {
        if ( orderItemRequest == null ) {
            return null;
        }

        Long productId = null;
        Integer orderCount = null;

        productId = orderItemRequest.getProductId();
        orderCount = orderItemRequest.getOrderCount();

        OrderFacadeDTO orderFacadeDTO = new OrderFacadeDTO( productId, orderCount );

        return orderFacadeDTO;
    }

    protected List<OrderFacadeDTO> orderItemRequestListToOrderFacadeDTOList(List<OrderItemRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderFacadeDTO> list1 = new ArrayList<OrderFacadeDTO>( list.size() );
        for ( OrderItemRequest orderItemRequest : list ) {
            list1.add( orderItemRequestToOrderFacadeDTO( orderItemRequest ) );
        }

        return list1;
    }
}
