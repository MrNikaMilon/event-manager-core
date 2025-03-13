package com.nikamilon.api.service;

import com.nikamilon.api.entity.UserEntity;

public interface AuthService {
    String generateAccessToken(UserEntity userDetails);

    String generateRefreshToken(UserEntity userDetails);

    UserEntity authenticate(String email, String password);

    boolean isValidToken(String token, UserEntity userDetails);

    boolean isExpiredToken(String token);
}
