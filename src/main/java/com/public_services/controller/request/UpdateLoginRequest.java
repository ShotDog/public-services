package com.public_services.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLoginRequest {

    private String email;
    private String password;
    private Long roleId;

}
