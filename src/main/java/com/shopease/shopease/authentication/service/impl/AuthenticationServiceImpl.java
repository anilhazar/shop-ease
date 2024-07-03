package com.shopease.shopease.authentication.service.impl;

import com.shopease.shopease.authentication.dto.ChangePasswordRequest;
import com.shopease.shopease.authentication.dto.LoginRequest;
import com.shopease.shopease.authentication.exception.AuthenticationException;
import com.shopease.shopease.authentication.service.AuthenticationService;
import com.shopease.shopease.common.exception.BadRequestException;
import com.shopease.shopease.customer.exception.CustomerNotFoundException;
import com.shopease.shopease.customer.model.dto.CustomerCreateRequest;
import com.shopease.shopease.customer.model.entity.CustomerEntity;
import com.shopease.shopease.customer.model.mapper.CustomerMapper;
import com.shopease.shopease.customer.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(CustomerCreateRequest customerCreateRequest) {
        if (customerRepository.findByEmailAndIsDeletedFalse(customerCreateRequest.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already taken");
        }

        String encodedPassword = passwordEncoder.encode(customerCreateRequest.getPassword());

        CustomerEntity customer = CustomerMapper.toEntity(customerCreateRequest);
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);

    }

    @Override
    public void login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        CustomerEntity customer = customerRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        CustomerEntity customer = customerRepository.findByEmailAndIsDeletedFalse(changePasswordRequest.getEmail())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email " + changePasswordRequest.getEmail()));

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), customer.getPassword())) {
            throw new AuthenticationException("Old password is incorrect");
        }

        customer.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        customerRepository.save(customer);
    }

}
