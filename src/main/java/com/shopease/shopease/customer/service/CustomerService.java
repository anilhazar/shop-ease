package com.shopease.shopease.customer.service;

import com.shopease.shopease.customer.model.dto.CustomerGetByEmailRequest;
import com.shopease.shopease.customer.model.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse getCustomerById(String id);

    CustomerResponse getCustomerByEmail(CustomerGetByEmailRequest customerGetByEmailRequest);

    List<CustomerResponse> getAllCustomers();

    void deleteCustomer(String id);
}
