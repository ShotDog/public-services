package com.public_services.service.impl;

import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.CreateUserRequest;
import com.public_services.controller.request.UpdateUserRequest;
import com.public_services.controller.response.UserResponse;
import com.public_services.entity.LoginEntity;
import com.public_services.entity.UserInfoEntity;
import com.public_services.mapper.UserMapper;
import com.public_services.repository.UserRepository;
import com.public_services.service.LoginService;
import com.public_services.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final LoginService loginService;

    @Override
    public Long create(CreateUserRequest createUserRequest) {
        Long loginEntityId = loginService.create(new CreateLoginRequest(createUserRequest.getEmail(), createUserRequest.getPassword()));

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setLoginEntity(new LoginEntity().setId(loginEntityId));
        return userRepository.save(userInfoEntity).getId();
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<UserInfoEntity> users = userRepository.findAll(pageable);
        return users.map(userMapper::toResponse);
    }

    @Override
    public UserResponse findById(Long id) {
        UserInfoEntity user = getById(id);
        return userMapper.toResponse(user);
    }

    @Override
    public void update(Long id, UpdateUserRequest updateUserRequest) {
        UserInfoEntity userInfoEntity = getById(id);
        update(userInfoEntity, updateUserRequest);
        userRepository.save(userInfoEntity);
    }

    private UserInfoEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void update(UserInfoEntity userInfoEntity, UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.getFullName() != null) {
            userInfoEntity.setFullName(updateUserRequest.getFullName());
        }
        if (updateUserRequest.getEmail() != null) {
            userInfoEntity.getLoginEntity().setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getLocation() != null) {
            userInfoEntity.setLocation(updateUserRequest.getLocation());
        }
        if (updateUserRequest.getPassportNumber() != null) {
            userInfoEntity.setPassportNumber(updateUserRequest.getPassportNumber());
        }
    }
}
