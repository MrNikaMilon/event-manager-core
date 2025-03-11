package com.nikamilon.api.dto.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserDTO(
        @NotNull
        @Size(min = 2, max = 50)
        String name,

        @Email
        String email,

        @NotNull
        @Size(min = 5, max = 50)
        String password,

        @Positive
        Integer age) { }
