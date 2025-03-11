package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.dtos.UserDTO;
import com.nikamilon.api.dto.response.UserResponse;
import com.nikamilon.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponse createUser(UserDTO user) {
        return null;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return null;
    }
}
