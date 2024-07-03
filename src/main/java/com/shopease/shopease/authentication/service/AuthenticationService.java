package com.shopease.shopease.authentication.service;

import com.shopease.shopease.authentication.dto.ChangePasswordRequest;
import com.shopease.shopease.authentication.dto.LoginRequest;
import com.shopease.shopease.customer.model.dto.CustomerCreateRequest;

public interface AuthenticationService {
    void register(CustomerCreateRequest customerCreateRequest);

    void login(LoginRequest loginRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
