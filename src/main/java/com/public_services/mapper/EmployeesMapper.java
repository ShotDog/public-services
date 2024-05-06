package com.public_services.mapper;

import com.public_services.controller.request.CreateEmployeeRequest;
import com.public_services.controller.response.EmployeeResponse;
import com.public_services.entity.EmployeeEntity;
import com.public_services.enums.Rate;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeesMapper {

    @Mapping(source = "organizationId", target = "organization.id")
    @Mapping(source = "rate", target = "rate", qualifiedByName = "mapStringEnum")
    EmployeeEntity toEntity(CreateEmployeeRequest createEmployeeRequest);

    @Mapping(source = "organization.id", target = "organizationId")
    CreateEmployeeRequest toDto(EmployeeEntity employeeEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "organization.id")
    EmployeeEntity partialUpdate(CreateEmployeeRequest createEmployeeRequest, @MappingTarget EmployeeEntity employeeEntity);

    @Mapping(source = "rate", target = "rate", qualifiedByName = "mapRateEnum")
    @Mapping(source = "login.email", target = "email")
    EmployeeResponse toResponse(EmployeeEntity employeeEntity);

    @Named("mapRateEnum")
    default String toStringRate(Rate rate) {
        return rate.getText();
    }

    @Named("mapStringEnum")
    default Rate toEnum(String rate) {
        if (rate == null) {
            return null;
        }
        return Rate.parseText(rate);
    }

}