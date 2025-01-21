package com.nikamilon.api.response;

import com.nikamilon.api.dto.EventDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserResponse(
        String name,
        String email,
        String password,
        String userRole,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<EventDto> events) {}
