package com.nikamilon.api.dto;

import com.nikamilon.api.entity.LocationEntity;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventDTO(
        String description,
        String name,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocationEntity location,
        String eventType,
        UserDTO userCreated,
        List<UserDTO> userId) { }
