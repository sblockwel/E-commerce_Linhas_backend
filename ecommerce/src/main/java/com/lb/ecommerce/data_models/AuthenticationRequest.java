package com.lb.ecommerce.data_models;

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
