package com.nikamilon.api.dto.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;


//Сервис валидации, мб через кастомную аннотации
@Builder
public record LocationDTO(
        @NotNull(message = "User name must not be null")
        @Size(min = 4, max = 20, message = "User name must be between 4 and 20 characters")
        String name,

        @NotNull(message = "Address must not be null")
        String address,

        @NotNull(message = "Capacity location must be null")
        @Positive
        Long capacity,

        @Size(max = 150, message = "The description should not exceed 150 characters")
        String description) { }
