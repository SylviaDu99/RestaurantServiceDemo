package org.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    @Value("${security.jwt.token.key}")
    private String key;

    public String createToken(AuthUserDetail userDetails){
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("permissions", userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
