package com.shopease.shopease.common.util;

import com.shopease.shopease.common.exception.InvalidStockException;
import com.shopease.shopease.product.model.entity.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StockValidator {

    public static void validateStock(ProductEntity product, Long quantity) {
        if (product.getStock() < quantity) {
            throw new InvalidStockException("Not enough stock for product: " + product.getName());
        }
        if (product.getStock() == 0) {
            throw new InvalidStockException("Product out of stock: " + product.getName());
        }
    }
}
