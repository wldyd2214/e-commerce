package com.hhplus.commerce.spring.application.cart.mapper;

import com.hhplus.commerce.spring.application.cart.dto.CartItemFacadeDTO;
import com.hhplus.commerce.spring.domain.cart.dto.request.CartCommand.AddItem;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartFacadeRequestMapper {

    default List<Long> toProductIds(List<CartItemFacadeDTO> cartItems) {
        return cartItems.stream()
                        .map(CartItemFacadeDTO::getProductId)
                        .collect(Collectors.toList());
    }

    AddItem toCartCommandAddItem(UserInfo userInfo, List<ProductInfo> productInfos, List<CartItemFacadeDTO> cartItems);
}
