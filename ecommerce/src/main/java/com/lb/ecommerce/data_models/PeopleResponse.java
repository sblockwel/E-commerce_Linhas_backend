package com.lb.ecommerce.data_models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PeopleResponse {
    private final String name;
    private final String email;
    private final String password;
    private final String zipCode;
    private final String number;
    private final String document;
}
