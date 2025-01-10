package com.nikamilon.api.controller;

import com.nikamilon.api.domain.dto.EventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events/")
public class LocationController {

    @GetMapping(path ="/locations")
    public ResponseEntity<List<EventDto>> getAllLocations() {
        return null;
    }

}
