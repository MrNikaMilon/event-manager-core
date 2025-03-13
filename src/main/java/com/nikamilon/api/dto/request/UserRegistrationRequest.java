package com.nikamilon.api.dto.request;

import lombok.Builder;

@Builder
public record UserRegistrationRequest(String name, String email, String password) {
}
