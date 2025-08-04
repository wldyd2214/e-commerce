package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.OrderFacadeDTO;
import com.hhplus.commerce.spring.old.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.old.application.order.mapper.OrderFacadeRequestMapper;
import com.hhplus.commerce.spring.old.domain.order.dto.request.OrderCommand;
import com.hhplus.commerce.spring.old.domain.product.dto.request.ProductCommand;
import com.hhplus.commerce.spring.old.domain.user.dto.UserCommand;
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
public class OrderFacadeRequestMapperImpl implements OrderFacadeRequestMapper {

    @Override
    public ProductCommand.Deduct toProductCommandDeduct(OrderFacadeRequest.Create create) {
        if ( create == null ) {
            return null;
        }

        List<ProductCommand.DeductProduct> products = null;

        products = mapOrdersToProducts( create.getOrders() );

        ProductCommand.Deduct deduct = new ProductCommand.Deduct( products );

        return deduct;
    }

    @Override
    public List<ProductCommand.DeductProduct> mapOrdersToProducts(List<OrderFacadeDTO> orders) {
        if ( orders == null ) {
            return null;
        }

        List<ProductCommand.DeductProduct> list = new ArrayList<ProductCommand.DeductProduct>( orders.size() );
        for ( OrderFacadeDTO orderFacadeDTO : orders ) {
            list.add( mapOrderToProduct( orderFacadeDTO ) );
        }

        return list;
    }

    @Override
    public ProductCommand.DeductProduct mapOrderToProduct(OrderFacadeDTO order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        Integer count = null;

        id = order.getProductId();
        count = order.getOrderCount();

        ProductCommand.DeductProduct deductProduct = new ProductCommand.DeductProduct( id, count );

        return deductProduct;
    }

    @Override
    public OrderCommand.Create toCreate(OrderFacadeRequest.Create request) {
        if ( request == null ) {
            return null;
        }

        List<OrderCommand.Create.OrderItem> orders = null;
        Long userId = null;

        orders = orderFacadeDTOListToOrderItemList( request.getOrders() );
        userId = request.getUserId();

        OrderCommand.Create create = new OrderCommand.Create( userId, orders );

        return create;
    }

    @Override
    public UserCommand.RewardPoint toUserCommandReward(Long userId, Integer deductionPoints) {
        if ( userId == null && deductionPoints == null ) {
            return null;
        }

        Long userId1 = null;
        userId1 = userId;
        Integer deductionPoints1 = null;
        deductionPoints1 = deductionPoints;

        UserCommand.RewardPoint rewardPoint = new UserCommand.RewardPoint( userId1, deductionPoints1 );

        return rewardPoint;
    }

    protected OrderCommand.Create.OrderItem orderFacadeDTOToOrderItem(OrderFacadeDTO orderFacadeDTO) {
        if ( orderFacadeDTO == null ) {
            return null;
        }

        Long productId = null;
        Integer orderCount = null;

        productId = orderFacadeDTO.getProductId();
        orderCount = orderFacadeDTO.getOrderCount();

        OrderCommand.Create.OrderItem orderItem = new OrderCommand.Create.OrderItem( productId, orderCount );

        return orderItem;
    }

    protected List<OrderCommand.Create.OrderItem> orderFacadeDTOListToOrderItemList(List<OrderFacadeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderCommand.Create.OrderItem> list1 = new ArrayList<OrderCommand.Create.OrderItem>( list.size() );
        for ( OrderFacadeDTO orderFacadeDTO : list ) {
            list1.add( orderFacadeDTOToOrderItem( orderFacadeDTO ) );
        }

        return list1;
    }
}
