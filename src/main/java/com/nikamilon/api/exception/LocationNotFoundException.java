package com.nikamilon.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends Exception {
  public LocationNotFoundException(String message) {
    super();
  }
}
