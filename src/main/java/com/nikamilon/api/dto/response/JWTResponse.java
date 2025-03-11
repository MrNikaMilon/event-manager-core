package com.nikamilon.api.dto.response;

import lombok.Builder;

@Builder
public record JWTResponse(
        String token,
        long expiresIn
) {
}
