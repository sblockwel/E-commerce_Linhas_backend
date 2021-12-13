package com.lb.ecommerce.services;

import com.lb.ecommerce.data_models.RegistrationRequest;
import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.utils.MapperUtils;
import com.lb.ecommerce.validators.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService implements IRegistrationService {

    private final IPeopleService peopleService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = peopleService.signUpUser(
                MapperUtils.map(request, Person.class)
        );

        return token;
    }
}
