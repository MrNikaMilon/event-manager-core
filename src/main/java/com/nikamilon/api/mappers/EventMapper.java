package com.nikamilon.api.mappers;

import com.nikamilon.api.dto.EventDTO;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.response.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EventMapper.class}
)
public interface EventMapper {

    EventMapper eventMapper = Mappers.getMapper(EventMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "eventType", target = "type", ignore = true),
            @Mapping(target = "dateCreated", ignore = true),
            @Mapping(target = "dateUpdate", ignore = true),
            @Mapping(source = "userId", target = "usersByEvent", ignore = true)
    })
    EventEntity eventDTOToEntity(EventDTO eventDTO);

    @Mappings({
            @Mapping(source = "type", target = "eventType", ignore = true),
            @Mapping(target = "dateCreated", ignore = true),
            @Mapping(target = "dateUpdate", ignore = true)
    })
    EventResponse eventEntityToResponse(EventEntity eventEntity);
}
