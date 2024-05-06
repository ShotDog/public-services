package com.public_services.service;

import com.public_services.controller.request.CreateRoleRequest;

public interface RoleService {

    Long create(CreateRoleRequest createRoleRequest);

    Long getDefaultRoleId();
}
