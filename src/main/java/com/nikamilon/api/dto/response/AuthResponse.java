package com.nikamilon.api.dto.response;

import lombok.Builder;

//класть в хэдеры jwt токен
@Builder
public record AuthResponse(String token, Long expiresIn) {
}
