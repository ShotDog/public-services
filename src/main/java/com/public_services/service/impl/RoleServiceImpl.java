package com.public_services.service.impl;

import com.public_services.controller.request.CreateRoleRequest;
import com.public_services.entity.RoleEntity;
import com.public_services.mapper.RoleMapper;
import com.public_services.repository.RoleRepository;
import com.public_services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Long create(CreateRoleRequest createRoleRequest) {
        RoleEntity roleEntity = roleMapper.toEntity(createRoleRequest);
        return roleRepository.save(roleEntity).getId();
    }

    @Override
    public Long getDefaultRoleId() {
//default id number for role USER
        return 1L;
    }
}
