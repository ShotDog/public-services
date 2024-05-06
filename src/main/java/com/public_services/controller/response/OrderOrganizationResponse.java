package com.public_services.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOrganizationResponse {

    private Long id;
    private String name;
    private String pan;
    private String email;
    private String address;
}
