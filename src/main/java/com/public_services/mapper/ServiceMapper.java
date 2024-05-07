package com.public_services.mapper;

import com.public_services.controller.request.CreateServiceRequest;
import com.public_services.controller.response.ServiceResponse;
import com.public_services.entity.OrganizationEntity;
import com.public_services.entity.ServiceEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface ServiceMapper {

    @Mapping(source = "organizationId", target = "organizationEntity", qualifiedByName = "mapOrganization")
    ServiceEntity toEntity(CreateServiceRequest createServiceRequest);

    ServiceResponse toResponse(ServiceEntity serviceEntity);

    @Named("mapOrganization")
    default OrganizationEntity mapOrganization(Long organizationId) {
        return new OrganizationEntity().setId(organizationId);
    }
}
