package com.nikamilon.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserDTO(
        String name,
        String email,
        String password,
        Integer age) { }
