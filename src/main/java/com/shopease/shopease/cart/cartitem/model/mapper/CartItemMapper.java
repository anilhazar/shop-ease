package com.shopease.shopease.cart.cartitem.model.mapper;

import com.shopease.shopease.cart.cartitem.model.dto.CartItemRequest;
import com.shopease.shopease.cart.cartitem.model.dto.CartItemResponse;
import com.shopease.shopease.cart.cartitem.model.entity.CartItemEntity;
import com.shopease.shopease.cart.model.entity.CartEntity;
import com.shopease.shopease.product.model.entity.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartItemMapper {

    public static CartItemEntity toEntity(CartItemRequest cartItemRequest) {
        return CartItemEntity.builder()
                .cart(CartEntity.builder().id(cartItemRequest.getCartId()).build())
                .product(ProductEntity.builder().id(cartItemRequest.getProductId()).build())
                .quantity(cartItemRequest.getQuantity())
                .price(cartItemRequest.getPrice())
                .build();
    }

    public static CartItemResponse toResponse(CartItemEntity cartItemEntity) {
        return CartItemResponse.builder()
                .id(cartItemEntity.getId())
                .cartId(cartItemEntity.getCart().getId())
                .productId(cartItemEntity.getProduct().getId())
                .quantity(cartItemEntity.getQuantity())
                .price(cartItemEntity.getPrice())
                .build();
    }
    
}
