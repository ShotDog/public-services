package com.public_services.mapper;

import com.public_services.controller.request.CreateLoginRequest;
import com.public_services.controller.request.LoginRequest;
import com.public_services.controller.request.UpdateLoginRequest;
import com.public_services.controller.response.LoginResponse;
import com.public_services.entity.LoginEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface LoginMapper {

    LoginEntity toEntity(CreateLoginRequest createLoginRequest);

    LoginEntity toEntity(UpdateLoginRequest updateLoginRequest);

    LoginResponse toResponse(LoginEntity loginEntity);

    LoginEntity toEntity(LoginRequest loginRequest);

    LoginRequest toDto(LoginEntity loginEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoginEntity partialUpdate(LoginRequest loginRequest, @MappingTarget LoginEntity loginEntity);
}
