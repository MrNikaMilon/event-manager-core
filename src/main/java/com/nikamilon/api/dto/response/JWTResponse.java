package com.nikamilon.api.dto.response;

import lombok.Builder;

//класть в хэдеры jwt токен
@Builder
public record JWTResponse(String accessToken, String refreshToken) {
}
