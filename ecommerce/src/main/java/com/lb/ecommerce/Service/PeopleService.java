package com.lb.ecommerce.Service;

import com.lb.ecommerce.Registration.AuthenticationRequest;
import com.lb.ecommerce.Security.JwtTokenUtil;
import com.lb.ecommerce.entity.ConfirmationToken;
import com.lb.ecommerce.entity.People;
import com.lb.ecommerce.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Data
@Getter
@Setter
public class PeopleService implements UserDetailsService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final PeopleRepository peopleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public People loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return peopleRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signInUser(AuthenticationRequest request) {

        boolean userExists = peopleRepository
                .findByEmail(request.getEmail())
                .isPresent();

        if (!userExists) {

            throw new IllegalStateException("User not registered");
        }

        People people = peopleRepository.findByEmail(request.getEmail()).get();

        String encodedPassword = bCryptPasswordEncoder
                .encode(request.getPassword());

        boolean passWordIsCorrect = encodedPassword == people.getPassword();

        if(passWordIsCorrect) {
            throw new IllegalStateException("Wrong password");
        }

        String token = jwtTokenUtil.generateToken(people);

        return token;
    }

    public String signUpUser(People people) {
        boolean userExists = peopleRepository
                .findByEmail(people.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(people.getPassword());

        people.setPassword(encodedPassword);

        peopleRepository.save(people);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                people
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);


        return token;
    }

    public int enableAppUser(String email) {
        return peopleRepository.enablePeople(email);
    }
}
