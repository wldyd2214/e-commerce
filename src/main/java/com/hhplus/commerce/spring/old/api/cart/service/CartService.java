package com.hhplus.commerce.spring.old.api.cart.service;

import com.hhplus.commerce.spring.old.api.cart.service.request.CartItemRegister;
import com.hhplus.commerce.spring.old.api.cart.service.request.CartRegisterRequest;
import com.hhplus.commerce.spring.old.api.cart.model.Cart;
import com.hhplus.commerce.spring.old.api.cart.model.CartItem;
import com.hhplus.commerce.spring.old.api.cart.repository.CartItemRepository;
import com.hhplus.commerce.spring.old.api.cart.repository.CartRepository;
import com.hhplus.commerce.spring.old.api.cart.service.response.CartServiceRes;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.domain.user.repository.UserQueryRepository;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final UserQueryRepository userQueryRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartServiceRes getCart(Long userId) {

//        User user = userRepository.findById(userId)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.USER_BAD_REQUEST));
//
//        Cart cart = cartRepository.findByUser(user)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.CART_BAD_REQUEST));
//
//        return CartServiceRes.toCartServiceRes(cart);
        return null;
    }

    @Transactional
    public Cart addCart(Long userId, CartRegisterRequest cartRegRequest) {

//        User user = userRepository.findById(userId)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.USER_BAD_REQUEST));

//        Cart cart = cartRepository.findByUser(user).orElse(Cart.create(user));
//        cartRepository.save(cart);
//
//        List<CartItem> cartItems = insertCartItem(cart, cartRegRequest);
//        cart.getCartItems().addAll(cartItems);
//
//        return cart;
        return null;
    }

    private List<CartItem> insertCartItem(Cart cart, CartRegisterRequest cartRegRequest) {
        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemRegister cartItemRegister : cartRegRequest.getCartItems()) {
            Product product =
                productRepository.findById(cartItemRegister.getProductId())
                                 .orElseThrow(() -> new CustomBadRequestException(
                                     BadRequestErrorCode.PRODUCT_BAD_REQUEST));

            CartItem cartItem = CartItem.create(cart, product, cartItemRegister.getOrderCount());
            cartItems.add(cartItem);
        }

        return cartItemRepository.saveAll(cartItems);
    }

    @Transactional
    public CartServiceRes removeCartItems(Long userId, List<Long> cartItemKeys) {

//        User user = userRepository.findById(userId)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.USER_BAD_REQUEST));
//
//        List<CartItem> cartItems = cartItemRepository.findAllById(cartItemKeys);
//        sameUserCheck(user, cartItems, cartItemKeys);
//
//        cartItemRepository.deleteAllByIdInBatch(cartItemKeys);
//
//        Cart cart = cartRepository.findByUser(user)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.CART_ITEM_BAD_REQUEST));
//
//        return CartServiceRes.toCartServiceRes(cart);

        return null;
    }

//    private void sameUserCheck(User user, List<CartItem> cartItems, List<Long> cartItemKeys) {
//        Map<Long, CartItem> cartItemMap = createProductMap(cartItems);
//        for (Long cartItemKey : new HashSet<>(cartItemKeys)) {
//            CartItem cartItem = cartItemMap.get(cartItemKey);
//
//            if (Objects.isNull(cartItem) || cartItem.getCart().getUser().getId() != user.getId()) {
//                throw new CustomForbiddenException(ForbiddenErrorCode.USER_FORBIDDEN);
//            }
//        }
//    }

    private Map<Long, CartItem> createProductMap(List<CartItem> cartItems) {
        return cartItems.stream()
                        .collect(Collectors.toMap(item -> item.getId(), i -> i));
    }
}
