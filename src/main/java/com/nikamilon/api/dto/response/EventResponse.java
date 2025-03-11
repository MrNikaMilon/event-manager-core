package com.nikamilon.api.dto.response;

import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.model.User;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventResponse(
        String name,
        String eventType,
        LocalDateTime dateEvent,
        Long occupiedPlaces,
        BigDecimal cost,
        Long duration,
        Long maxPlaces) {}
