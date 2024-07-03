package com.shopease.shopease.cart.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequest {
    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;
}
