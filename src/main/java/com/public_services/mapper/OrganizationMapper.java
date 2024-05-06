package com.public_services.mapper;

import com.public_services.controller.request.CreateOrganizationRequest;
import com.public_services.entity.OrganizationEntity;
import com.public_services.controller.response.OrganizationResponse;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface OrganizationMapper {

    OrganizationEntity toEntity(CreateOrganizationRequest organizationRequest);

    OrganizationEntity toEntity(OrganizationResponse organizationResponse);

    @Mapping(target = "email", source = "loginEntity.email")
    OrganizationResponse toDto(OrganizationEntity organizationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrganizationEntity partialUpdate(OrganizationResponse organizationResponse, @MappingTarget OrganizationEntity organizationEntity);
}
