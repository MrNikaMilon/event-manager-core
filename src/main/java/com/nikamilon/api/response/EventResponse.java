package com.nikamilon.api.response;

import com.nikamilon.api.dto.UserDto;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.model.User;

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
