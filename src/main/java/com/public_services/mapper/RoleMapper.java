package com.public_services.mapper;

import com.public_services.controller.request.CreateRoleRequest;
import com.public_services.entity.RoleEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface RoleMapper {

    RoleEntity toEntity(CreateRoleRequest createRoleRequest);

}
