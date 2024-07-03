package com.shopease.shopease.cart.model.mapper;

import com.shopease.shopease.cart.cartitem.model.mapper.CartItemMapper;
import com.shopease.shopease.cart.model.dto.CartRequest;
import com.shopease.shopease.cart.model.dto.CartResponse;
import com.shopease.shopease.cart.model.entity.CartEntity;
import com.shopease.shopease.customer.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartMapper {

    public static CartEntity toEntity(CartRequest cartRequest) {
        return CartEntity.builder()
                .customer(CustomerEntity.builder().id(cartRequest.getCustomerId()).build())
                .build();
    }

    public static CartResponse toResponse(CartEntity cartEntity) {
        return CartResponse.builder()
                .id(cartEntity.getId())
                .customerId(cartEntity.getCustomer().getId())
                .totalPrice(cartEntity.getTotalPrice())
                .items(cartEntity.getItems()
                        .stream()
                        .map(CartItemMapper::toResponse)
                        .toList())
                .build();
    }
    
}
