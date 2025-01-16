package com.nikamilon.api.response;

import com.nikamilon.api.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        String name,
        String email,
        String password,
        String userRole,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<EventDto> events) {}
