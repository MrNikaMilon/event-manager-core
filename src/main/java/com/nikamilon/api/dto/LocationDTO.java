package com.nikamilon.api.dto;

import lombok.Builder;

@Builder
public record LocationDTO(
        String name,
        String address,
        Long capacity,
        String description) { }
