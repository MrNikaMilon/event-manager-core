package com.nikamilon.api.mapper.impl;

import com.nikamilon.api.dto.LocationDto;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.mapper.EventMapper;
import com.nikamilon.api.mapper.LocationMapper;
import com.nikamilon.api.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationMapperImpl implements LocationMapper {

    private final EventMapperImpl eventMapper;

    @Override
    public LocationEntity toEntity(LocationDto dto) {
        return LocationEntity.builder()
                .address(dto.address())
                .capacity(dto.capacity())
                .description(dto.description())
                .dateUpdated(LocalDateTime.now())
                .details(dto.details())
                .events(dto.events().stream()
                        .map(eventMapper::toEntity)
                        .toList())
                .build();
    }

    @Override
    public LocationResponse toResponse(LocationDto dto) {
        return LocationResponse.builder()
                .address(dto.address())
                .capacity(dto.capacity())
                .description(dto.description())
                .details(dto.details())
                .events(dto.events().stream()
                        .map(eventMapper::toResponse)
                        .toList())
                .build();
    }
}
