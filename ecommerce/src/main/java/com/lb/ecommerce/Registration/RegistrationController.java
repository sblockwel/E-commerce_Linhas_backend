package com.lb.ecommerce.Registration;

import com.lb.ecommerce.Service.PeopleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@Data
public class RegistrationController {

    public final PeopleService peopleService;

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping(path = "/api/auth")
    public ResponseEntity<String> auth(AuthenticationRequest request) {

        try {
            String token = peopleService.signInUser(request);
            return ResponseEntity.ok(token);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}