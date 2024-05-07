package com.public_services.mapper;

import com.public_services.controller.request.CreateBookingRequest;
import com.public_services.controller.response.BookingResponse;
import com.public_services.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "isProcessed", defaultValue = "false")
    BookingEntity toEntity(CreateBookingRequest createBookingRequest);

    @Mapping(source = "employee.fullName", target = "employeeFullName")
    @Mapping(source = "user.fullName", target = "userFullName")
    BookingResponse toResponse(BookingEntity bookingEntity);
}
