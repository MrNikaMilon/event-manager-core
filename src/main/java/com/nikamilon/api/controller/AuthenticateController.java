package com.nikamilon.api.controller;

import com.nikamilon.api.dto.request.LoginRequest;
import com.nikamilon.api.dto.request.RefreshRequest;
import com.nikamilon.api.dto.request.UserRegistrationRequest;
import com.nikamilon.api.dto.response.JWTResponse;
import com.nikamilon.api.entity.TokenEntity;
import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.handler.exception.UserNotFoundException;
import com.nikamilon.api.model.dictionary.TokenType;
import com.nikamilon.api.model.dictionary.UserRole;
import com.nikamilon.api.repository.TokenRepository;
import com.nikamilon.api.repository.UserRepository;
import com.nikamilon.api.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthServiceImpl authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest user) {
        var registerUser = UserEntity.builder()
                .name(user.name())
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .role(Set.of(UserRole.ROLE_USER))
                .build();
        userRepository.save(registerUser);

        var accessToken = authService.generateAccessToken(registerUser);
        var refreshToken = authService.generateRefreshToken(registerUser);

        savedUserToken(registerUser, accessToken);

        return ResponseEntity.ok(JWTResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
        var loginUser = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UserNotFoundException(loginRequest.email()));
        revokeAllUser(loginUser);

        var accessToken = authService.generateAccessToken(loginUser);
        var refreshToken = authService.generateRefreshToken(loginUser);

        savedUserToken(loginUser, accessToken);

        return ResponseEntity.ok(JWTResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build()
        );
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest refreshRequest){
        var refreshToken = refreshRequest.token();
        var userId = authService.extractUserId(refreshToken);
        var foundUser = userRepository.findById(userId).orElseThrow();

        if(authService.isValidToken(refreshToken, foundUser)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var accessToken = authService.generateAccessToken(foundUser);

        savedUserToken(foundUser, accessToken);

        return ResponseEntity.ok(JWTResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserEntity userEntity){
        revokeAllUser(userEntity);
        return ResponseEntity.ok().build();
    }

    private void savedUserToken(UserEntity userEntity, String token){
        var savedToken = TokenEntity.builder()
                .user(userEntity)
                .token(token)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(savedToken);
    }

    private void revokeAllUser(UserEntity userEntity){
        var validToken = tokenRepository.findAllValidTokenByUserId(userEntity.getId());
        if(validToken.isEmpty()) return;

        validToken.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validToken);
    }


}
