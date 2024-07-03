package com.shopease.shopease.authentication.controller;


import com.shopease.shopease.authentication.dto.ChangePasswordRequest;
import com.shopease.shopease.authentication.dto.LoginRequest;
import com.shopease.shopease.authentication.service.AuthenticationService;
import com.shopease.shopease.customer.model.dto.CustomerCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody final CustomerCreateRequest customerCreateRequest) {
        authenticationService.register(customerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginCustomer(@Valid @RequestBody final LoginRequest loginRequest) {
        authenticationService.login(loginRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid final ChangePasswordRequest changePasswordRequest) {
        authenticationService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }
}
