package com.shopease.shopease.cart.cartitem.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemChangeQuantityRequest {
    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be positive")
    private Long quantity;

}
