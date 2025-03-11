package com.nikamilon.api.mappers;

import com.nikamilon.api.dto.dtos.EventDTO;
import com.nikamilon.api.dto.request.EventCreateRequestDTO;
import com.nikamilon.api.dto.request.EventUpdateRequestDTO;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.model.dictionary.EventType;
import com.nikamilon.api.dto.response.EventResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EventMapper.class}
)
public interface EventMapper {

    @Mappings({
            @Mapping(target = "location", source = "locationId", ignore = true),
            @Mapping(source = "eventType", target = "type", qualifiedByName = "mapEventType"),
    })
    EventEntity eventCreationDTOToEntity(EventCreateRequestDTO eventCreateRequestDTO);

    @Mappings({
            @Mapping(target = "location", source = "locationId", ignore = true),
            @Mapping(source = "eventType", target = "type", qualifiedByName = "mapEventType"),
    })
    EventEntity eventCreationDTOToEntity(EventUpdateRequestDTO eventUpdateRequestDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "eventType", target = "type", qualifiedByName = "mapEventType"),
    })
    EventEntity eventDTOToEntity(EventDTO eventDTO);

    @Mapping(source = "type", target = "eventType", qualifiedByName = "mapEventTypeString")
    EventResponse eventEntityToResponse(EventEntity eventEntity);

    @Named("mapEventType")
    default EventType mapEventType(String eventType) {
        return EventType.getEventByString(eventType);
    }

    @Named("mapEventTypeString")
    default String mapEventType(EventType eventType) {
        return EventType.getDescriptionEvents(eventType);
    }
}
