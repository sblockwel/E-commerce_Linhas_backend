package com.lb.ecommerce.Registration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class AuthenticationRequest {

    private final String email;
    private final String password;
}
