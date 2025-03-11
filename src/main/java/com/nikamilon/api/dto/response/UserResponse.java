package com.nikamilon.api.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserResponse(
        String name,
        String email,
        String password,
        String userRole) {}
