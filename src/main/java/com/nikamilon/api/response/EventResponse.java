package com.nikamilon.api.response;

import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.model.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventResponse(
        String description,
        String name,
        LocationEntity location,
        String eventType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdate,
        UserResponse userCreated,
        List<UserResponse> usersByEvent) {}
