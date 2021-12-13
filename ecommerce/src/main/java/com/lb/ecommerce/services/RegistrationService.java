package com.lb.ecommerce.services;

import com.lb.ecommerce.data_models.RegistrationRequest;
import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.validators.EmailValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PeopleService peopleService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = peopleService.signUpUser(
                modelMapper.map(request, Person.class)
        );

        return token;
    }
}
