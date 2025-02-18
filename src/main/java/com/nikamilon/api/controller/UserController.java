package com.nikamilon.api.controller;

import com.nikamilon.api.dto.UserCredentials;
import com.nikamilon.api.dto.UserDTO;
import com.nikamilon.api.model.User;
import com.nikamilon.api.response.UserResponse;
import com.nikamilon.api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(
                userService.createUser(user),
                HttpStatus.CREATED);
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(
                userService.getUserById(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("/users/auth")
    public ResponseEntity<String> getAuthUser(
            @RequestBody UserCredentials userCredentials
    ) {
        return new ResponseEntity<>(
                "data",
                HttpStatus.OK
        );
    }
}
