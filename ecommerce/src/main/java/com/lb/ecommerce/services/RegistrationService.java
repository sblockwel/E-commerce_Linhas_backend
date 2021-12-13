package com.lb.ecommerce.services;

import com.lb.ecommerce.data_models.RegistrationRequest;
import com.lb.ecommerce.entity.People;
import com.lb.ecommerce.models.UserRole;
import com.lb.ecommerce.validators.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PeopleService peopleService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = peopleService.signUpUser(
                new People(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.CLIENT

                )
        );

        return token;
    }
}
