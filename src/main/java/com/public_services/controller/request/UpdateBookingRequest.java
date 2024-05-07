package com.public_services.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingRequest {

    private String serviceName;
    private LocalDateTime dateTime;
    private Long employeeId;
    private Long userId;

}
