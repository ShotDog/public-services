package com.public_services.mapper;

import com.public_services.controller.request.CreateOrderOrganizationRequest;
import com.public_services.controller.response.OrderOrganizationResponse;
import com.public_services.entity.OrderOrganizationEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.FIELD)
public interface OrderOrganizationMapper {

    OrderOrganizationEntity toEntity(CreateOrderOrganizationRequest createOrderOrganizationRequest);

    OrderOrganizationResponse toResponse(OrderOrganizationEntity orderOrganizationEntity);
}
