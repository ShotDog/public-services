package com.public_services.mapper;

import com.public_services.controller.response.UserResponse;
import com.public_services.entity.UserInfoEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    @Mapping(target = "email", source = "loginEntity.email")
    UserResponse toResponse(UserInfoEntity user);

}
