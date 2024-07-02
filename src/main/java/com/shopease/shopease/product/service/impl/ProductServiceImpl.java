package com.shopease.shopease.product.service.impl;

import com.shopease.shopease.product.exception.InvalidProductDataException;
import com.shopease.shopease.product.exception.ProductNotFoundException;
import com.shopease.shopease.product.model.dto.ProductRequest;
import com.shopease.shopease.product.model.dto.ProductResponse;
import com.shopease.shopease.product.model.entity.ProductEntity;
import com.shopease.shopease.product.model.mapper.ProductMapper;
import com.shopease.shopease.product.repository.ProductRepository;
import com.shopease.shopease.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        validateProductRequest(productRequest);
        ProductEntity product = ProductMapper.toEntity(productRequest);
        productRepository.save(product);

    }

    public ProductResponse getProductById(String id) {
        return productRepository.findById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public ProductResponse getProductByName(String name) {
        return productRepository.findByName(name)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        validateProductRequest(productRequest);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        ProductEntity product = ProductMapper.toEntity(productRequest);
        product.setId(id);
        productRepository.save(product);

    }

    public void deleteProduct(String id) {
        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    private void validateProductRequest(ProductRequest productRequest) {
        if (productRequest.getName() == null || productRequest.getName().isBlank()) {
            throw new InvalidProductDataException("Product name cannot be null or blank");
        }
        if (productRequest.getPrice() == null || productRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductDataException("Product price must be greater than zero");
        }
        if (productRequest.getStock() == null || productRequest.getStock() < 0) {
            throw new InvalidProductDataException("Product stock cannot be negative");
        }
    }
}
