package com.nikamilon.api.domain.dto;

import com.nikamilon.api.domain.entity.LocationEntity;
import com.nikamilon.api.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record EventDto(
        String description,
        String name,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocationEntity location,
        String eventType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdate,
        User userCreated,
        List<UserDto> usersByEvent) { }
