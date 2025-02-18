package com.nikamilon.api.response;

import com.nikamilon.api.model.Event;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LocationResponse(
        String address,
        Long capacity,
        String description,
        String details,
        List<EventResponse> events) { }
