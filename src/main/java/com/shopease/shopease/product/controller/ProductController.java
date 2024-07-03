package com.shopease.shopease.product.controller;

import com.shopease.shopease.product.model.dto.ProductRequest;
import com.shopease.shopease.product.model.dto.ProductResponse;
import com.shopease.shopease.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable @UUID final String id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable final String name) {
        ProductResponse productResponse = productService.getProductByName(name);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponses = productService.getAllProducts();
        return ResponseEntity.ok(productResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable @UUID final String id,
                                              @RequestBody @Valid final ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @UUID final String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

