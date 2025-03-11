package com.nikamilon.api.handler.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends EntityNotFoundException {
  public LocationNotFoundException(String message) {
    super();
  }
}
