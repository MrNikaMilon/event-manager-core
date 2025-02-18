package com.nikamilon.api.mappers;

import com.nikamilon.api.dto.UserDTO;
import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {EventMapper.class}
)
public interface UserMapper {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "userRole", target = "role"),
            @Mapping(source = "eventsId", target = "events")
    })
    UserEntity userDTOToEntity(UserDTO userDTO);

    @Mapping(source = "role", target = "userRole")
    UserResponse userEntityToResponse(UserEntity userEntity);

}
