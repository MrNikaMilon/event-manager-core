package com.nikamilon.api.dto.response;

import com.nikamilon.api.model.Event;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LocationResponse(
        String name,
        String address,
        Long capacity,
        String description) { }
