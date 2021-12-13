package com.lb.ecommerce.utils;

import com.lb.ecommerce.entity.People;
import com.lb.ecommerce.models.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Data
@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    //retorna o username do token jwt
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retorna expiration date do token jwt
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);

    }

    //para retornar qualquer informação do token nos iremos precisar da secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //gera token para user
    public String generateToken(People people) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "user");
        if (people.getUserRole() == UserRole.ADMIN) {
            claims.put("role", "admin");
        }
        return doGenerateToken(claims, people.getEmail());
    }

    //Cria o token e devine tempo de expiração pra ele
    private String doGenerateToken(Map<String, Object> claims, String username) {
        byte[] secretKey = secret.getBytes(StandardCharsets.UTF_8);
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuer("localhost:8080")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(Keys.hmacShaKeyFor(secretKey))
                .compact();
        return jwtToken;
    }

    //valida o token
    public Boolean validateToken(String token, People people) {
        final String username = getUsernameFromToken(token);
        return (username.equals(people.getUsername()) && !isTokenExpired(token));
    }
}
