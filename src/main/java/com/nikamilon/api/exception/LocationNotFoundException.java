package com.nikamilon.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LocationNotFoundException extends ResponseStatusException {
  public LocationNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
