package com.public_services.mapper;

import com.public_services.controller.request.CreateEmployeeRequest;
import com.public_services.controller.response.EmployeeResponse;
import com.public_services.entity.Employee;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeesMapper {
    @Mapping(source = "organizationId", target = "organization.id")
    Employee toEntity(CreateEmployeeRequest createEmployeeRequest);

    @Mapping(source = "organization.id", target = "organizationId")
    CreateEmployeeRequest toDto(Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "organizationId", target = "organization.id")
    Employee partialUpdate(CreateEmployeeRequest createEmployeeRequest, @MappingTarget Employee employee);

    EmployeeResponse toResponse(Employee employee);
}