package com.public_services.service;

import com.public_services.controller.request.UpdateUserRequest;
import com.public_services.controller.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse findById(Long id);

    void update(Long id, UpdateUserRequest updateUserRequest);
}
