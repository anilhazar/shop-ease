package com.shopease.shopease.product.model.mapper;

import com.shopease.shopease.product.model.dto.ProductRequest;
import com.shopease.shopease.product.model.dto.ProductResponse;
import com.shopease.shopease.product.model.entity.ProductEntity;
import com.shopease.shopease.product.model.enums.Category;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductMapper {

    public static ProductEntity toEntity(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .category(Category.valueOf(String.valueOf(productRequest.getCategory())))
                .build();
    }

    public static ProductResponse toResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .category(Category.valueOf(String.valueOf(productEntity.getCategory())))
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }

    public static List<ProductResponse> toResponse(
            final List<ProductEntity> productEntities
    ) {
        return productEntities.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

}
