package com.nikamilon.api.model;

import com.nikamilon.api.model.dictionary.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Event> events;
}
