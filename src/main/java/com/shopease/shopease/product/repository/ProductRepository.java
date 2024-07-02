package com.shopease.shopease.product.repository;

import com.shopease.shopease.product.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByName(String name);

}
