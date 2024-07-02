package com.shopease.shopease.customer.repository;

import com.shopease.shopease.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmailAndIsDeletedFalse(String email);

    Optional<CustomerEntity> findByIdAndIsDeletedFalse(String id);

    List<CustomerEntity> findAllByIsDeletedFalse();
}
