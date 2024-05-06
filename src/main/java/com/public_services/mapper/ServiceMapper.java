package com.public_services.mapper;

import com.public_services.controller.request.CreateServiceRequest;
import com.public_services.controller.response.ServiceResponse;
import com.public_services.entity.ServiceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface ServiceMapper {

    ServiceEntity toEntity(CreateServiceRequest createServiceRequest);

    ServiceResponse toResponse(ServiceEntity serviceEntity);
}
