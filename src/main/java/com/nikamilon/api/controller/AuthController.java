package com.nikamilon.api.controller;


import com.nikamilon.api.dto.request.LoginRequest;
import com.nikamilon.api.dto.response.AuthResponse;
import com.nikamilon.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        var user = authService.authenticate(
                loginRequest.email(),
                loginRequest.password()
        );
        var authResponse =  AuthResponse.builder()
                .token(authService.generateToken(user))
                .expiresIn(86400L)
                .build();
        return ResponseEntity.ok(authResponse);
    }
}
