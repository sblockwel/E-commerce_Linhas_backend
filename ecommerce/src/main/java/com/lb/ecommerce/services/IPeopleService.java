package com.lb.ecommerce.services;

import com.lb.ecommerce.data_models.AuthenticationRequest;
import com.lb.ecommerce.entity.Person;

public interface IPeopleService {
    String signUpUser(Person person);
    int enableAppUser(String email);
    String signInUser(AuthenticationRequest request);
    Person loadUserByUsername(String email);
}
