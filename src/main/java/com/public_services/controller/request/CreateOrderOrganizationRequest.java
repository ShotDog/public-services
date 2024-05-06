package com.public_services.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderOrganizationRequest {

    private String name;
    private String address;
    private String email;
    private String pan;

}
