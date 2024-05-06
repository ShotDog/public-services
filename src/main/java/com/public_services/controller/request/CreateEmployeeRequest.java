package com.public_services.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    private String fullName;
    private String position;
    private Long organizationId;
    private String email;
    private String rate;
}