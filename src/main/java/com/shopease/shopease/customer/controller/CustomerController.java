package com.shopease.shopease.customer.controller;

import com.shopease.shopease.authentication.service.AuthenticationService;
import com.shopease.shopease.customer.model.dto.CustomerCreateRequest;
import com.shopease.shopease.customer.model.dto.CustomerGetByEmailRequest;
import com.shopease.shopease.customer.model.dto.CustomerResponse;
import com.shopease.shopease.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    private final AuthenticationService authenticationService;

    public CustomerController(final CustomerService customerService, AuthenticationService authenticationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody @Valid final CustomerCreateRequest customerCreateRequest) {
        authenticationService.register(customerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable @UUID final String id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping("/email")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(
            @RequestBody @Valid final CustomerGetByEmailRequest customerGetByEmailRequest) {
        CustomerResponse customerResponse = customerService.getCustomerByEmail(customerGetByEmailRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable @UUID final String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
