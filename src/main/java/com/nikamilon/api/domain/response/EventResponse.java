package com.nikamilon.api.domain.response;

import com.nikamilon.api.domain.dto.UserDto;
import com.nikamilon.api.domain.entity.LocationEntity;
import com.nikamilon.api.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record EventResponse(
        String description,
        String name,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        LocationEntity location,
        String eventType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdate,
        User userCreated,
        List<UserDto> usersByEvent) {}
