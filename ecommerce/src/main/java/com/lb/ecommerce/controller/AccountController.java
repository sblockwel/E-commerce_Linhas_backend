package com.lb.ecommerce.controller;

import com.lb.ecommerce.data_models.AuthenticationRequest;
import com.lb.ecommerce.data_models.RegistrationRequest;
import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.models.UserRole;
import com.lb.ecommerce.repository.PeopleRepository;
import com.lb.ecommerce.services.RegistrationService;
import com.lb.ecommerce.services.PeopleService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@RestController
@Data
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private final PeopleService peopleService;

    @Autowired
    private final RegistrationService registrationService;

    @Autowired
    private final PeopleRepository peopleRepository;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            return ResponseEntity.ok(registrationService.register(request));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping("auth")
    public ResponseEntity<String> auth(@RequestBody AuthenticationRequest request) {
        try {
            String token = peopleService.signInUser(request);
            return ResponseEntity.ok(token);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<String> getKey() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        byte[] rawData = key.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(rawData);
        return ResponseEntity.ok(encodedKey);
    }

    @PostMapping("admin/:email")
    public ResponseEntity setAdmin(String email) {
        try{
            boolean personExists = peopleRepository
                    .findByEmail(email)
                    .isPresent();

            if (!personExists){
                return ResponseEntity.badRequest().body("Email not exists");
            }

            Person person = peopleRepository
                    .findByEmail(email)
                    .get();

            UserRole oldRole = person.getUserRole();

            person.setUserRole(oldRole == UserRole.ADMIN ? UserRole.CLIENT : UserRole.ADMIN);

            peopleRepository.save(person);

            return ResponseEntity.ok("");
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
