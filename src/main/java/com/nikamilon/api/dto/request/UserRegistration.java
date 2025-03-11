package com.nikamilon.api.dto.request;

import lombok.Builder;

@Builder
public record UserRegistration(
        String email,
        String password
) {
}
