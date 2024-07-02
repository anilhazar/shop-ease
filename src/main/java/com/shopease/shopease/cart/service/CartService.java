package com.shopease.shopease.cart.service;

import com.shopease.shopease.cart.model.dto.CartRequest;
import com.shopease.shopease.cart.model.dto.CartResponse;

public interface CartService {
    void createCart(CartRequest cartRequest);

    CartResponse getCartByCustomerId(String customerId);

    CartResponse emptyCart(String cartId);

    void deleteCart(String id);
}
