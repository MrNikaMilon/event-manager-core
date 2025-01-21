package com.nikamilon.api.mapper.impl;

import com.nikamilon.api.dto.EventDto;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.mapper.EventMapper;
import com.nikamilon.api.response.EventResponse;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements EventMapper {
    @Override
    public EventResponse toResponse(EventEntity entity) {
        return EventResponse.
    }

    @Override
    public EventEntity toEntity(EventDto dto) {
        return null;
    }
}
