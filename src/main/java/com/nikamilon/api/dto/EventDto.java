package com.nikamilon.api.dto;

import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.model.User;

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
