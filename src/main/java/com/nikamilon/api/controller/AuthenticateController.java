package com.nikamilon.api.controller;

import com.nikamilon.api.dto.request.UserRegistration;
import com.nikamilon.api.dto.response.JWTResponse;
import com.nikamilon.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/login")
    public ResponseEntity<JWTResponse> login(@RequestBody UserRegistration userRegistration) {
        var userDetails = authenticationService.authenticate(
                userRegistration.email(),
                userRegistration.password()
        );
        log.info("User {} authenticated", userDetails.getUsername());
        var token = authenticationService.generateToken(userDetails);
        var authResponse = JWTResponse.builder()
                .token(token)
                .expiresIn(86400)
                .build();
        log.info("Info: {}", authResponse);
        return ResponseEntity.ok(authResponse);
    }
}
