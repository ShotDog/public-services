package com.public_services.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResponse {
    private String name;
    private String pan;
    private String location;
    private String email;
}