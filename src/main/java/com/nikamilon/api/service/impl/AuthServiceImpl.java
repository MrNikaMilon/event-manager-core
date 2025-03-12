package com.nikamilon.api.service.impl;

import com.nikamilon.api.security.EventUserDetailsService;
import com.nikamilon.api.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EventUserDetailsService eventUserDetailsService;

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret-key}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiresIn;

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return eventUserDetailsService.loadUserByUsername(email);
    }

    @Override
    public UserDetails validateToken(String token) {
        var userName = extractUserName(token);
        return eventUserDetailsService.loadUserByUsername(userName);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .claim("email", userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiresIn))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String extractUserName(String token) {
        return extractAllClaims(token).getSubject();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
