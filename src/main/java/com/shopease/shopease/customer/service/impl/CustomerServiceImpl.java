package com.shopease.shopease.customer.service.impl;

import com.shopease.shopease.customer.exception.CustomerNotFoundException;
import com.shopease.shopease.customer.model.dto.CustomerGetByEmailRequest;
import com.shopease.shopease.customer.model.dto.CustomerResponse;
import com.shopease.shopease.customer.model.entity.CustomerEntity;
import com.shopease.shopease.customer.model.mapper.CustomerMapper;
import com.shopease.shopease.customer.repository.CustomerRepository;
import com.shopease.shopease.customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        return customerRepository.findByIdAndIsDeletedFalse(id)
                .map(CustomerMapper::toResponse)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));
    }

    @Override
    public CustomerResponse getCustomerByEmail(CustomerGetByEmailRequest customerGetByEmailRequest) {
        String email = customerGetByEmailRequest.getEmail();
        return customerRepository.findByEmailAndIsDeletedFalse(email)
                .map(CustomerMapper::toResponse)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email " + email));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAllByIsDeletedFalse().stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .build())
                .toList();
    }

    @Override
    public void deleteCustomer(String id) {
        CustomerEntity customer = customerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));
        if (customer.getIsDeleted()) {
            throw new RuntimeException("Customer Already Deleted with id: " + id);
        }
        customer.setIsDeleted(true);
        customerRepository.save(customer);
    }
}
