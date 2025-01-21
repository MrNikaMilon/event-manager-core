package com.nikamilon.api.mapper;

import com.nikamilon.api.dto.EventDto;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.response.EventResponse;

public interface EventMapper  {
    EventResponse toResponse(EventEntity entity);

    EventEntity toEntity(EventDto dto);
}
