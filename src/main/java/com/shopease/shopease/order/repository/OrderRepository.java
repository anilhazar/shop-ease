package com.shopease.shopease.order.repository;

import com.shopease.shopease.order.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByCustomerIdAndIsDeletedFalse(String customerId);

    Optional<OrderEntity> findByIdAndIsDeletedFalse(String orderId);
}
