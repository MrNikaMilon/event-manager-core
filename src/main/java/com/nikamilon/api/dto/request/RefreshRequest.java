package com.nikamilon.api.dto.request;

import lombok.Builder;

@Builder
public record RefreshRequest(String token) {
}
