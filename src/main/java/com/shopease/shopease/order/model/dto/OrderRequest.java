package com.shopease.shopease.order.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    
    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;
}
