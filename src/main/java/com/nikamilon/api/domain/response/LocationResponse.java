package com.nikamilon.api.domain.response;

import com.nikamilon.api.domain.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public record LocationResponse(
        String address,
        Long capacity,
        String description,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String details,
        List<Event> events) { }
