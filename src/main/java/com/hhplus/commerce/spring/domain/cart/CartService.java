package com.hhplus.commerce.spring.domain.cart;

import com.hhplus.commerce.spring.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.domain.cart.mapper.CartServiceResponseMapper;
import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.model.CartItem;
import com.hhplus.commerce.spring.domain.cart.model.CartProduct;
import com.hhplus.commerce.spring.domain.cart.model.CartUser;
import com.hhplus.commerce.spring.domain.cart.repository.command.CartItemCommandRepository;
import com.hhplus.commerce.spring.domain.cart.repository.command.CartCommandRepository;
import com.hhplus.commerce.spring.domain.cart.repository.query.CartQueryRepository;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartCommandRepository cartCommandRepository;
    private final CartQueryRepository cartQueryRepository;
    private final CartItemCommandRepository cartItemCommandRepository;

    private final CartServiceResponseMapper responseMapper;

    @Transactional(readOnly = true)
    public CartInfo getCartInfoByUserId(Long userId) {
        Cart cart = findByUserId(userId);
        return responseMapper.toCartInfo(cart);
    }

    @Transactional
    public CartInfo addCartItems(CartCommand.AddItem command) {
        Long userId = command.getUserInfo().getId();
        Cart cart = cartQueryRepository.findByUserId(userId)
                                       .orElseGet(() -> createCart(command.getUserInfo()));

        List<CartItem> cartItems = makeCartItemList(cart, command.getProductInfos(), command.getCartItems());
        cart.updateCartItems(cartItems);

        return responseMapper.toCartInfo(cart);
    }

    @Transactional
    public CartInfo removeCartItems(CartCommand.RemoveCartItem command) {

        // 1. Cart 존재하는지 조회한다.
        Cart cart = findByUserId(command.getUserId());

        // 2. Cart 존재하는 경우 제거한다.
        cart.removeCartItems(command.getCartItemIds());

        return responseMapper.toCartInfo(cart);
    }

    private Cart findByUserId(Long userId) {
        return cartQueryRepository.findByUserId(userId)
                                  .orElseThrow(() -> new CustomBadRequestException(BadRequestErrorCode.CART_BAD_REQUEST));
    }

    private Cart createCart(UserInfo userInfo) {
        Cart cart = Cart.create(CartUser.create(userInfo.getId()));
        return cartCommandRepository.save(cart);
    }

    private List<CartItem> makeCartItemList(Cart cart, List<ProductInfo> productInfos, List<CartCommand.CartItem> commandCartItems) {

        // Given
        Map<Long, CartCommand.CartItem> cartItemMap = createCartItemMap(commandCartItems);
        List<CartItem> cartItems = cart.getCartItems();

        //  이미 등록된 상품 ID Set
        List<Long> savedProductIds =
            cartItems.stream().map(cartItem -> cartItem.getProduct().getId()).collect(Collectors.toList());

        for (ProductInfo productInfo : productInfos) {
            if(!savedProductIds.contains(productInfo.getId())) {
                CartProduct product = CartProduct.create(productInfo.getId(), productInfo.getName(), productInfo.getPrice());
                CartCommand.CartItem item = cartItemMap.get(product.getId());
                cartItems.add(CartItem.create(cart, product, item.getCartQuantity()));
            }
        }

        return cartItemCommandRepository.saveAll(cartItems);
    }

    private Map<Long, CartCommand.CartItem> createCartItemMap(List<CartCommand.CartItem> commandCartItems) {
        return commandCartItems.stream()
            .collect(Collectors.toMap(CartCommand.CartItem::getProductId, i -> i));
    }
}
