package com.shopease.shopease.cart.repository;

import com.shopease.shopease.cart.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, String> {
    Optional<CartEntity> findByCustomerIdAndIsDeletedFalse(String customerId);

    Optional<CartEntity> findByIdAndIsDeletedFalse(String cartId);
}
