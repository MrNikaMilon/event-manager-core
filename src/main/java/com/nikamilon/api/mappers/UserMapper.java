package com.nikamilon.api.mappers;

import com.nikamilon.api.dto.dtos.UserDTO;
import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.dto.response.UserResponse;
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

    UserEntity userDTOToEntity(UserDTO userDTO);

    UserResponse userEntityToResponse(UserEntity userEntity);

}
