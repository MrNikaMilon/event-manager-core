package com.nikamilon.api.service.impl;

import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.handler.exception.UserNotFoundException;
import com.nikamilon.api.repository.UserRepository;
import com.nikamilon.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.security.secret}")
    private String secret;

    @Value("${jwt.security.access-token-time}")
    private long accessTokenTime;

    @Value("${jwt.security.refresh-token-time}")
    private long refreshTokenTime;

    private String buildToken(UserEntity user, long expiresIn) {
        var userRole = String.join(user.getRole().toString(), ", ");
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("role", userRole)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiresIn))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Long extractUserId(String token) {
        var userId = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.parseLong(userId);
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public UserEntity authenticate(String email, String password) {
        var foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        var isPasswordEquals = passwordEncoder.encode(password).equals(foundUser.getPassword());
        if(isPasswordEquals){
            return foundUser;
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public String generateAccessToken(UserEntity userDetails) {
        return buildToken(userDetails, this.accessTokenTime);
    }

    @Override
    public String generateRefreshToken(UserEntity userDetails) {
        return buildToken(userDetails, this.refreshTokenTime);
    }

    @Override
    public boolean isValidToken(String token, UserEntity userDetails) {
        val userName = extractUserId(token);
        return (userName.equals(userDetails.getUsername())) && !isExpiredToken(token);
    }

    @Override
    public boolean isExpiredToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }


}
