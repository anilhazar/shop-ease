package com.shopease.shopease.product.service;

import com.shopease.shopease.product.model.dto.ProductRequest;
import com.shopease.shopease.product.model.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    ProductResponse getProductById(String id);

    ProductResponse getProductByName(String name);

    List<ProductResponse> getAllProducts();

    void updateProduct(String id, ProductRequest productRequest);

    void deleteProduct(String id);
}
