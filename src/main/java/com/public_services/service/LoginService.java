package com.public_services.service;

import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.LoginRequest;
import com.public_services.controller.request.UpdateLoginRequest;
import com.public_services.controller.response.LoginResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginService {

    Long create(CreateLoginRequest createLoginRequest);

    String login(LoginRequest loginRequest);

    Page<LoginResponse> findAll(Pageable pageable);

    void update(Long id, UpdateLoginRequest updateLoginRequest);

    void delete(Long id);

}
