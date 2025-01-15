package com.nikamilon.api.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserDto(
        String name,
        String email,
        String password,
        String userRole,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<EventDto>events) { }
