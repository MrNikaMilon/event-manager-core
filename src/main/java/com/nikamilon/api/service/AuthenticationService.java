package com.nikamilon.api.service;

import com.nikamilon.api.security.EventManagerUserDetails;

public interface AuthenticationService {

    EventManagerUserDetails authenticate(String email, String password);

    String generateToken(EventManagerUserDetails userDetails);

    String extractUsername(String token);

    EventManagerUserDetails getUserDetails(String token);

    boolean isTokenValid(String token, EventManagerUserDetails user);
}
