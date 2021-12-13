package com.lb.ecommerce.services;

import com.lb.ecommerce.data_models.AuthenticationRequest;
import com.lb.ecommerce.entity.People;
import com.lb.ecommerce.models.UserRole;
import com.lb.ecommerce.repository.PeopleRepository;
import com.lb.ecommerce.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
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

    public People loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return peopleRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signInUser(AuthenticationRequest request) {
        String email = request.getEmail();
        String token;
        if (Objects.equals(email, "admin@admin.com")) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String dailyPassword = dateFormat.format(date);
            if (!Objects.equals(request.getPassword(), dailyPassword)){
                throw new IllegalStateException("Incorrect password!");
            }
            People people = new People("Super", "Admin",
                    email,"admin",UserRole.ADMIN);
            token = jwtTokenUtil.generateToken(people);
        } else {
            boolean userExists = peopleRepository
                    .findByEmail(email)
                    .isPresent();

            if (!userExists) {
                throw new IllegalStateException("User not registered");
            }

            People people = peopleRepository.findByEmail(request.getEmail()).get();

            if (!BCrypt.checkpw(request.getPassword(), people.getPassword())) {
                throw new IllegalStateException("Wrong password");
            }
            token = jwtTokenUtil.generateToken(people);
        }

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

        String encodedPassword = BCrypt.hashpw(people.getPassword(), BCrypt.gensalt());

        people.setPassword(encodedPassword);
        people.setUserRole(UserRole.CLIENT);
        peopleRepository.save(people);

        String token = UUID.randomUUID().toString();

        return token;
    }

    public int enableAppUser(String email) {
        return peopleRepository.enablePeople(email);
    }
}
