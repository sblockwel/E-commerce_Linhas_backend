package com.lb.ecommerce.data_models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
public class PeopleResponse {
    private String name;
    private String email;
    private String password;
    private String zipCode;
    private String number;
    private String document;
}
