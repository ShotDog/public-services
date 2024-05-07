package com.public_services.mapper;

import com.public_services.controller.request.CreateBookingRequest;
import com.public_services.controller.response.BookingResponse;
import com.public_services.entity.BookingEntity;
import com.public_services.entity.EmployeeEntity;
import com.public_services.entity.ServiceEntity;
import com.public_services.entity.UserInfoEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "isProcessed", constant = "false")
    @Mapping(source = "employeeId", target = "employee", qualifiedByName = "mapEmployee")
    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
    @Mapping(source = "serviceId", target = "service", qualifiedByName = "mapService")
    BookingEntity toEntity(CreateBookingRequest createBookingRequest);

    @Mapping(source = "employee.fullName", target = "employeeFullName")
    @Mapping(source = "user.fullName", target = "userFullName")
    BookingResponse toResponse(BookingEntity bookingEntity);

    @Named("mapEmployee")
    default EmployeeEntity mapEmployee(Long employeeId) {
        return new EmployeeEntity().setId(employeeId);
    }

    @Named("mapUser")
    default UserInfoEntity mapUser(Long userId) {
        return new UserInfoEntity().setId(userId);
    }

    @Named("mapService")
    default ServiceEntity mapService(Long serviceId) {
        return new ServiceEntity().setId(serviceId);
    }
}
