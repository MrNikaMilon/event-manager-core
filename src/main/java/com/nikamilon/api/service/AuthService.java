package com.nikamilon.api.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails authenticate(String username, String password);
    UserDetails validateToken(String token);
    String generateToken(UserDetails userDetails);
}
