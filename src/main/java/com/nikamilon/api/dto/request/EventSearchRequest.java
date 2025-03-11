package com.nikamilon.api.dto.request;

import com.nikamilon.api.model.dictionary.EventStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventSearchRequest(
        String name,
        int placesMin,
        int placesMax,
        LocalDateTime dateStartAfter,
        LocalDateTime dateStartBefore,
        BigDecimal costMin,
        BigDecimal costMax,
        int durationMin,
        int durationMax,
        Long locationId,
        List<EventStatus> eventStatus) { }
