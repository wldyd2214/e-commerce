package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.OrderFacadeDTO;
import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.domain.order.dto.request.OrderCommand;
import com.hhplus.commerce.spring.domain.product.dto.request.ProductCommand;
import com.hhplus.commerce.spring.domain.product.dto.request.ProductCommand.DeductProduct;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand.RewardPoint;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderFacadeRequestMapper {

    @Mapping(target = "products", source = "orders") // orders를 products로 매핑
    ProductCommand.Deduct toProductCommandDeduct(OrderFacadeRequest.Create create);

    // orders (List<OrderFacadeDTO>) → products (List<ProductCommand.DeductProduct>) 변환을 위한 메서드
    List<DeductProduct> mapOrdersToProducts(List<OrderFacadeDTO> orders);

    @Mapping(target = "id", source = "productId") // OrderFacadeDTO의 productId를 DeductProduct의 id로 매핑
    @Mapping(target = "count", source = "orderCount") // OrderFacadeDTO의 orderCount를 DeductProduct의 count로 매핑
    ProductCommand.DeductProduct mapOrderToProduct(OrderFacadeDTO order);

    OrderCommand.Create toCreate(OrderFacadeRequest.Create request);

    UserCommand.RewardPoint toUserCommandReward(long userId, int deductionPoints);
}
