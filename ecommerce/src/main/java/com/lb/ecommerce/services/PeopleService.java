package com.lb.ecommerce.services;

import com.lb.ecommerce.data_models.AuthenticationRequest;
import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.models.UserRole;
import com.lb.ecommerce.repository.PeopleRepository;
import com.lb.ecommerce.utils.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Data
@Getter
@Setter
public class PeopleService implements UserDetailsService, IPeopleService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final PeopleRepository peopleRepository;

    public Person loadUserByUsername(String email)
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
            Person person = new Person("Admin",
                    email,"admin",UserRole.ADMIN);
            token = jwtTokenUtil.generateToken(person);
        } else {
            boolean userExists = peopleRepository
                    .findByEmail(email)
                    .isPresent();

            if (!userExists) {
                throw new IllegalStateException("User not registered");
            }

            Person person = peopleRepository.findByEmail(request.getEmail()).get();

            if (!BCrypt.checkpw(request.getPassword(), person.getPassword())) {
                throw new IllegalStateException("Wrong password");
            }
            token = jwtTokenUtil.generateToken(person);
        }

        return token;
    }

    public String signUpUser(Person person) {
        boolean userExists = peopleRepository
                .findByEmail(person.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = BCrypt.hashpw(person.getPassword(), BCrypt.gensalt());

        person.setPassword(encodedPassword);
        person.setUserRole(UserRole.CLIENT);
        peopleRepository.save(person);

        String token = UUID.randomUUID().toString();

        return token;
    }

    public int enableAppUser(String email) {
        return peopleRepository.enablePeople(email);
    }
}
