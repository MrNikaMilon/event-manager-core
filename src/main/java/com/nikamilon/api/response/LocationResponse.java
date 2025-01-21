package com.nikamilon.api.response;

import com.nikamilon.api.model.Event;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LocationResponse(
        String address,
        Long capacity,
        String description,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String details,
        List<EventResponse> events) { }
