package com.hhplus.commerce.spring.old.application.cart;

import com.hhplus.commerce.spring.old.application.cart.dto.CartFacadeRequest;
import com.hhplus.commerce.spring.old.application.cart.mapper.CartFacadeRequestMapper;
import com.hhplus.commerce.spring.old.domain.cart.CartService;
import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.old.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.domain.product.service.ProductService;
import com.hhplus.commerce.spring.old.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.old.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartFacade {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    private final CartFacadeRequestMapper requestMapper;

    public CartInfo processAddCartItems(CartFacadeRequest.AddItem request) {

        // 1. 사용자 정보 조회
        UserInfo userInfo = userService.findUserInfoById(request.getUserId());

        // 2. 상품 정보 조회
        List<Long> productIds = requestMapper.toProductIds(request.getCartItems());
        List<ProductInfo> productInfos = productService.getProducts(productIds);

        // 3. 장바구니 조회
        CartCommand.AddItem command = requestMapper.toCartCommandAddItem(userInfo, productInfos, request.getCartItems());
        CartInfo cartInfo = cartService.addCartItems(command);

        return cartInfo;
    }
}
