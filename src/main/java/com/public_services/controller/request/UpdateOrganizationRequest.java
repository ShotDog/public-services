package com.public_services.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrganizationRequest {

    private String name;
    private String address;
    private List<String> services;
}
