package com.nikamilon.api.dto;

import com.nikamilon.api.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public record LocationDto(
        String address,
        Long capacity,
        String description,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String details,
        List<EventDto>events) { }
