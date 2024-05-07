package com.public_services.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingResponse {

    private Long id;
    private String serviceName;
    private LocalDateTime dateTime;
    private String employeeFullName;
    private String userFullName;

}
