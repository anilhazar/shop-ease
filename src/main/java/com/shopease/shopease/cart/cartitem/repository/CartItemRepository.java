package com.shopease.shopease.cart.cartitem.repository;

import com.shopease.shopease.cart.cartitem.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, String> {
    Optional<CartItemEntity> findByIdAndIsDeletedFalse(String id);

    Optional<CartItemEntity> findByCartIdAndProductIdAndIsDeletedFalse(String cartId, String productId);

    List<CartItemEntity> findAllByCartIdAndIsDeletedFalse(String cartId);
}
