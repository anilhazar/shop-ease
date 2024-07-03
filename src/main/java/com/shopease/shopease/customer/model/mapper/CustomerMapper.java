package com.shopease.shopease.customer.model.mapper;

import com.shopease.shopease.customer.model.dto.CustomerCreateRequest;
import com.shopease.shopease.customer.model.dto.CustomerResponse;
import com.shopease.shopease.customer.model.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CustomerMapper {

    public static CustomerEntity toEntity(CustomerCreateRequest customerCreateRequest) {
        return CustomerEntity.builder()
                .name(customerCreateRequest.getName())
                .email(customerCreateRequest.getEmail())
                .password(customerCreateRequest.getPassword())
                .build();
    }

    public static CustomerResponse toResponse(CustomerEntity customerEntity) {
        return CustomerResponse.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdatedAt())
                .build();
    }

    public static List<CustomerResponse> toResponse(List<CustomerEntity> customerEntities) {
        return customerEntities.stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }
}
