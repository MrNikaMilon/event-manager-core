package com.nikamilon.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record EventUpdateRequestDTO(
        @NotNull(message = "Name event must not be null")
        String name,

        @Min(0)
        int maxPlaces,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime date,

        @Min(0)
        BigDecimal cost,

        String eventType,

        @Min(30)
        int duration,

        @Positive
        Long locationId
) {
}
