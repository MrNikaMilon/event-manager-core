package com.nikamilon.api.service.impl;

import com.nikamilon.api.security.EventManagerUserDetails;
import com.nikamilon.api.security.EventManagerUserDetailsImpl;
import com.nikamilon.api.service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final EventManagerUserDetailsImpl userDetailsService;

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret-key}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public EventManagerUserDetails authenticate(String email, String password) {
        return (EventManagerUserDetails) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        ).getPrincipal();
    }
    @Override
    public String generateToken(EventManagerUserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public EventManagerUserDetails getUserDetails(String token) {
        val email = extractUsername(token);
        return userDetailsService.loadUserByUsername(email);
    }

    public boolean isTokenValid(String token, EventManagerUserDetails user) {
        val email = extractUsername(token);
        return email.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
