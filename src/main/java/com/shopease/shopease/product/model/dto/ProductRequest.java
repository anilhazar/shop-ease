package com.shopease.shopease.product.model.dto;

import com.shopease.shopease.product.model.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 45, message = "Name must be between 2 and 45 characters")
    private String name;

    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Stock cannot be null")
    @Positive(message = "Stock must be positive")
    private Long stock;

    @NotNull(message = "Category cannot be null")
    private Category category;
}
