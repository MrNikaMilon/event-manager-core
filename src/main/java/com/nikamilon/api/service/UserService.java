package com.nikamilon.api.service;

import com.nikamilon.api.dto.UserDTO;
import com.nikamilon.api.response.UserResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    UserResponse createUser(UserDTO user);

    UserResponse getUserById(Long userId);
}
