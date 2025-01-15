package com.nikamilon.api.mapper;

import com.nikamilon.api.domain.dto.LocationDto;
import com.nikamilon.api.domain.entity.LocationEntity;
import com.nikamilon.api.domain.model.Location;
import com.nikamilon.api.domain.response.LocationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LocationMapper {
    LocationDto toDto(Location location);

    Location toModel(LocationDto locationDto);

    LocationEntity toEntity(Location location);

    List<LocationResponse> toListResponse(List<Location> locations);

    LocationResponse toResponse(Location location);
}
