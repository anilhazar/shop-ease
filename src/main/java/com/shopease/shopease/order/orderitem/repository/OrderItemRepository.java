package com.shopease.shopease.order.orderitem.repository;

import com.shopease.shopease.order.orderitem.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {
    Optional<OrderItemEntity> findByOrderIdAndIsDeletedFalse(String orderId);

    Optional<OrderItemEntity> findByOrderIdAndProductIdAndIsDeletedFalse(String orderId, String productId);

    List<OrderItemEntity> findAllByIsDeletedFalse();
}
