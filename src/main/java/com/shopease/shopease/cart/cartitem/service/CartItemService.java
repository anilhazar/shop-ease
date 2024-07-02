package com.shopease.shopease.cart.cartitem.service;

import com.shopease.shopease.cart.cartitem.model.dto.CartItemChangeQuantityRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemResponse;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItemRequest cartItemRequest);

    CartItemResponse getCartItemById(String id);

    void updateCartItemQuantity(String itemId, CartItemChangeQuantityRequest changeQuantityRequest);

    List<CartItemResponse> getCartItemsByCartId(String cartId);

    void removeCartItem(String id);
}
