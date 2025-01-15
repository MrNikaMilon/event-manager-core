package com.nikamilon.api.exception;

import java.time.LocalDateTime;

public record ErrorObject(
        Integer statusCode,
        String message,
        LocalDateTime timestamp) { }
