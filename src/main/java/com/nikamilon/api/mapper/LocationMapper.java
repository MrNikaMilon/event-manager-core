package com.nikamilon.api.mapper;

import com.nikamilon.api.dto.LocationDto;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.response.LocationResponse;

public interface LocationMapper {
    LocationEntity toEntity(LocationDto dto);

    LocationResponse toResponse(LocationDto dto);
}
