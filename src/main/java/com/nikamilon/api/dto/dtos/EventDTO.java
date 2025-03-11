package com.nikamilon.api.dto.dtos;

import com.nikamilon.api.entity.LocationEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventDTO(
        String description,
        String name,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocationEntity location,
        String eventType,
        UserDTO userCreated) { }
