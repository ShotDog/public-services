package com.public_services.entity.metadata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginEntity_ {

    public static final String TABLE_NAME = "logins";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID = "role_id";
}
