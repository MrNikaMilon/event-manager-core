package com.nikamilon.api.service;

import com.nikamilon.api.dto.dtos.UserDTO;
import com.nikamilon.api.dto.response.UserResponse;


public interface UserService {
    UserResponse createUser(UserDTO user);

    UserResponse getUserById(Long userId);
}
