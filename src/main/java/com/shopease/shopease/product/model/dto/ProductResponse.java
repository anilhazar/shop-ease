package com.shopease.shopease.product.model.dto;

import com.shopease.shopease.product.model.enums.Category;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
