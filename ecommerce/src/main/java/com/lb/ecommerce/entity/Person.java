package com.lb.ecommerce.entity;

import com.lb.ecommerce.models.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "people")
public class Person implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String name;

    private String email;

    @NotNull
    private String password;

     @NotNull
     @Pattern(regexp="^[0-9-]*$")
     @Size(min = 10, max = 10)
     private String zipCode;

     @NotNull
     @Min(0)
     private int number; // phone number

     @NotNull
     @Pattern(regexp="^[0-9.-]*$")
     @Size(min = 11,max = 14)
     private String document; //pode ser CPF ou CNPJ (para empresas)

    @Enumerated(EnumType.STRING)
    private UserRole userRole; // C para cliente e A para o adminstrador

    private Boolean locked = true;
    private Boolean enabled = true;

    public Person(String name,
                  String email,
                  String password,
                  UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
