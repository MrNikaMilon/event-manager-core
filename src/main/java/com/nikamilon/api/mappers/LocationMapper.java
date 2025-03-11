package com.nikamilon.api.mappers;

import com.nikamilon.api.dto.dtos.LocationDTO;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.dto.response.LocationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EventMapper.class}
)
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "nameLocation", source = "name")
    LocationEntity locationDTOToEntity(LocationDTO locationDTO);

    @Mapping(source = "nameLocation", target = "name")
    LocationResponse locationEntityToResponse(LocationEntity locationEntity);
}
