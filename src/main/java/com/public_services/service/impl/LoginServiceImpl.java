package com.public_services.service.impl;

import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.LoginRequest;
import com.public_services.controller.request.UpdateLoginRequest;
import com.public_services.controller.response.LoginResponse;
import com.public_services.entity.LoginEntity;
import com.public_services.entity.RoleEntity;
import com.public_services.mapper.LoginMapper;
import com.public_services.repository.LoginRepository;
import com.public_services.service.LoginService;
import com.public_services.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;

    private final RoleService roleService;

    @Override
    public Long create(CreateLoginRequest createLoginRequest) {
        if (createLoginRequest.getPassword() == null) {
            createLoginRequest.setPassword("12345678");
        }
        LoginEntity loginEntity = loginMapper.toEntity(createLoginRequest);

        Long roleId = roleService.getDefaultRoleId();
        loginEntity.setRoleEntity(new RoleEntity().setId(roleId));
        return loginRepository.save(loginEntity).getId();
    }

    @Override
    public String login(LoginRequest loginRequest) {
        LoginEntity loginEntity = loginRepository.findByEmail(loginRequest.getEmail()).orElseThrow(EntityNotFoundException::new);
        if (loginRequest.getPassword().equals(loginEntity.getPassword())) {
            return loginEntity.getRoleEntity().getName();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Page<LoginResponse> findAll(Pageable pageable) {
        Page<LoginEntity> userEntities = loginRepository.findAll(pageable);
        return userEntities.map(loginMapper::toResponse);
    }

    @Override
    public void update(Long id, UpdateLoginRequest updateLoginRequest) {
        LoginEntity loginEntity = getById(id);
        update(loginEntity, updateLoginRequest);
        loginRepository.save(loginEntity);
    }

    @Override
    public void delete(Long id) {
        LoginEntity loginEntity = getById(id);
        loginRepository.delete(loginEntity);
    }

    private LoginEntity getById(Long id) {
        return loginRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void update(LoginEntity loginEntity, UpdateLoginRequest updateLoginRequest) {
        if (updateLoginRequest.getPassword() != null) {
            loginEntity.setPassword(updateLoginRequest.getPassword());
        }
        if (updateLoginRequest.getEmail() != null) {
            loginEntity.setEmail(updateLoginRequest.getEmail());
        }
        if (updateLoginRequest.getRoleId() != null) {
            loginEntity.setRoleEntity(new RoleEntity().setId(updateLoginRequest.getRoleId()));
        }
    }
}
